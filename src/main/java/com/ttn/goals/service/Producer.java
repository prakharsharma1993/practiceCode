package com.ttn.goals.service;

import com.ttn.goals.co.UserCO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void run(UserCO userCO) throws Exception {

        rabbitTemplate.convertAndSend("goal-exchange", "foo.bar.#", userCO);


    }



}
