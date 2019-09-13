package com.ttn.goals.service;


import com.ttn.goals.co.UserCO;
import com.ttn.goals.dao.UserRepositoryService;
import com.ttn.goals.dto.UserDTO;
import com.ttn.goals.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepositoryService userRepositoryService;

  /*  @Autowired
    PasswordEncoder passwordEncoder;*/


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


}
