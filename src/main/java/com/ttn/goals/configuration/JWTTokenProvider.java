package com.ttn.goals.configuration;

import com.sun.net.httpserver.HttpServer;
import com.ttn.goals.dto.UserDTO;
import com.ttn.goals.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;

@Component
@Slf4j
public class JWTTokenProvider {

    @Autowired
    UserService userService;


    public String generateToken(Authentication authentication, HttpServletRequest httpServletRequest) {

        String userId = null;

        if (authentication.getPrincipal() instanceof String) {

            userId = ((String) authentication.getPrincipal()).toString();
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            userId = userDetails.getUsername();
        }


        Date now = new Date();
        Date expiryTime = new Date(now.getTime() + 360000);
        String ipAddress = getClientIpAddress(httpServletRequest);
        String userAgent = getUserAgent(httpServletRequest);
        UserDTO userDTO = userService.validateUserID(userId);
        Long id = userDTO.getId();
        String password = userDTO.getPassword();

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("ipAddress", ipAddress);
        claims.put("userAgent", userAgent);
        claims.put("password", password);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryTime)
                .signWith(SignatureAlgorithm.HS512, "JWTSuperSecretKey")
                .compact();
    }

    public String getUserAgent(HttpServletRequest httpServletRequest) {

        String userAgent = httpServletRequest.getHeader("User-Agent");
        return userAgent;

    }

    public String getClientIpAddress(HttpServletRequest httpServletRequest) {

        String ip = httpServletRequest.getHeader("X-Forwarded-For");
        if (Objects.nonNull(ip) || ip.length() != 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }

    public Date getDateFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey("JWTSuperSecretKey").parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("JWTSuperSecretKey")
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getPasswordFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("JWTSuperSecretKey")
                .parseClaimsJws(token)
                .getBody();
        return claims.get(PASSWORD).toString();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey("JWTSuperSecretKey").parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("invalid.jwt.signature");
        } catch (MalformedJwtException ex) {
            log.error("invalid.jwt.token");
        } catch (ExpiredJwtException ex) {
            log.error("expired.jwt.token");
        } catch (UnsupportedJwtException ex) {
            log.error("unsupported.jwt.token");
        } catch (IllegalArgumentException ex) {
            log.error("jwt.token.empty");
        }
        return false;
    }
}
