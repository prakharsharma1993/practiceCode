package com.ttn.goals.controller;


import com.ttn.goals.co.LoginRequestCO;
import com.ttn.goals.co.UserCO;
import com.ttn.goals.dto.ResponseDTO;
import com.ttn.goals.dto.UserDTO;
import com.ttn.goals.service.UserService;
import com.ttn.goals.validator.UserValidator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    ResponseDTO createUser(@Valid @RequestBody UserCO userCO,BindingResult bindingResult) throws Exception{


        validateRequest(userValidator,bindingResult,userCO);
        Long userId = userService.createUser(userCO);

        return new ResponseDTO(true,"User Created Successfully",userId);

    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    ResponseDTO getUserDetails(@PathVariable(value = "id") Long id){

        UserDTO userDTO = userService.getUserDetails(id);


        return new ResponseDTO(true,"User Details fetched successfully",userDTO);
    }

    @PostMapping("/signIn")
    public ResponseDTO authenticateUser(@Valid @RequestBody LoginRequestCO loginRequestCO,
                                        HttpServletRequest httpServletRequest) {

        return userService.authenticateUser(loginRequestCO, httpServletRequest);
    }



}
