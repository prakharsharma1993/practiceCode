package com.ttn.goals ;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EntityScan({"com.ttn.goals.model"})
@EnableJpaRepositories({"com.ttn.goals.dao"})
@ComponentScan({"com.ttn.goals.controller","com.ttn.goals.service","com.ttn.goals.validator"})
public class GoalsApplications {


    public static void main(String[] args) {
        SpringApplication.run(GoalsApplications.class, args);
    }


}
