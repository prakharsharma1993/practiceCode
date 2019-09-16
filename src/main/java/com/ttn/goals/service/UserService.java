package com.ttn.goals.service;


import com.ttn.goals.co.LoginRequestCO;
import com.ttn.goals.co.UserCO;
import com.ttn.goals.configuration.JWTTokenProvider;
import com.ttn.goals.dao.UserRepositoryService;
import com.ttn.goals.dto.JwtAuthenticationResponseDTO;
import com.ttn.goals.dto.LoginResponseDTO;
import com.ttn.goals.dto.ResponseDTO;
import com.ttn.goals.dto.UserDTO;
import com.ttn.goals.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.Date;
import java.util.Optional;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class UserService {

    @Autowired
    JWTTokenProvider jwtTokenProvider;
    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    public Long createUser(UserCO userCO){


        User user = User.builder()
                .userId(userCO.getUserId())
                .password(new BCryptPasswordEncoder().encode(userCO.getPassword()))
                .email(userCO.getEmail())
                .address(userCO.getAddress())
                .mobileNumber(userCO.getMobileNumber())
                .name(userCO.getName())
                .build();

        User userDao =userRepositoryService.save(user);
        return userDao.getId();
    }


    public UserDTO getUserDetails(Long id){

        Optional<User> userDao = userRepositoryService.findById(id);
        User user = userDao.isPresent()? userDao.get():null;
        UserDTO userDTO = UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .address(user.getAddress())
                .build();
        return userDTO;
    }


    public ResponseDTO authenticateUser (LoginRequestCO loginRequestCO,
                                         HttpServletRequest httpServletRequest){

        log.info("Inside Authentication");
       Authentication authentication = authenticationManager.authenticate
               (new UsernamePasswordAuthenticationToken(loginRequestCO.getUserID(),loginRequestCO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication,httpServletRequest);

        Date expirationTime = jwtTokenProvider.getDateFromJwt(token);
        Optional<User> userDao = userRepositoryService.findByUserIdAndActive(loginRequestCO.getUserID(),true);

        UserDTO userDto = getUserDto(userDao);
        JwtAuthenticationResponseDTO jwtResponse = new JwtAuthenticationResponseDTO(token, expirationTime);


        return new ResponseDTO(true,"User Logged In Successfully", new LoginResponseDTO(userDto,jwtResponse));

    }

    public UserDTO validateUserID(String userId) {
        Optional<User> optionalUser = userRepositoryService.findByUserIdAndActive(userId, true);
        UserDTO userDto = getUserDto(optionalUser);
        return userDto;
    }

    private UserDTO getUserDto(Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDto = new UserDTO();
            userDto.setName(user.getName());
            userDto.setPassword(user.getPassword());
            userDto.setEmail(user.getEmail());
            userDto.setAddress(user.getAddress());
            userDto.setMobileNumber(user.getMobileNumber());
            userDto.setUserId(user.getUserId());
            userDto.setId(user.getId());
            return userDto;
        }
        return null;
    }

}
