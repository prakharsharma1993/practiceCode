package com.ttn.goals.configuration;



//import com.investindia.core.service.CustomUserDetailsService;
import com.ttn.goals.dao.UserRepositoryService;
import com.ttn.goals.model.User;
import com.ttn.goals.service.CustomUserServiceDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;


import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;


@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {



    @Autowired
    UserRepositoryService userRepositoryService;
    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    CustomUserServiceDetail customUserServiceDetail;

    public static String getJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws
            ServletException, IOException {
        try {
            log.info("Url::" + request.getRequestURL().toString());
            String jwtToken = getJwtFromRequest(request);
            Date expiryDate=tokenProvider.getDateFromJwt(jwtToken);
            Authentication newAuthentication=SecurityContextHolder.getContext().getAuthentication();
            long currentTime=System.currentTimeMillis();
          /*  if((currentTime-expiryDate.getTime())>55 && (currentTime-expiryDate.getTime())<60)
            {*/
            response.setHeader("token",tokenProvider.generateToken(newAuthentication,request));
//            }
//            log.info(messageByLocaleService.getMessage("request.payload") + request);
            if (StringUtils.hasText(jwtToken) && tokenProvider.validateToken(jwtToken)) {
                log.info("first if block called");
                String userId = tokenProvider.getUserIdFromJWT(jwtToken);
                String password=tokenProvider.getPasswordFromJWT(jwtToken);
//                log.info("userId fetched");
                if (userId != null) {
                    Optional<User> optionalUser = userRepositoryService.findByUserIdAndActive(userId,true);
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        if (user.isActive() && userId.equals(user.getUserId()) && password.equals(user.getPassword())) {
                            UserDetails userDetails = customUserServiceDetail.loadUserByUsername(userId);
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            log.info("Token Obtained+++++++++++++++++++++++++++");
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            response.setHeader("token",tokenProvider.generateToken(authentication,request));
                        }
                    } else {
                        throw new UsernameNotFoundException("jwt.token.does.not.exist");
                    }
                } else {
                    throw new UsernameNotFoundException("user.not.logged.in");
                }
            }
        } catch (Exception ex) {
            log.error(("could.not.set.user.authentication"), ex);
        }

        filterChain.doFilter(request, response);
    }
}

