package com.ttn.goals.service;


import com.ttn.goals.co.UserCO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RabbitListener(queues = "${spring.rabbitmq.getconection-queue}")
public class Consumer  {

    @Autowired
    UserService userService;

    @RabbitHandler
    public void receiveMessage(UserCO userCO) throws Exception{
       try{

           userService.createUser(userCO);

       }catch (Exception e){
           log.error("Exception Arises while consuming the resource ::"+ e);
       }

    }



}
