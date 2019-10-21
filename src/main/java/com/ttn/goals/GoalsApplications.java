package com.ttn.goals ;


import com.ttn.goals.service.JavaStreamExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan({"com.ttn.goals.model"})
@EnableJpaRepositories({"com.ttn.goals.dao"})
@ComponentScan
public class GoalsApplications {


    public static void main(String[] args) {

        JavaStreamExample javaStreamExample = new JavaStreamExample();
        javaStreamExample.streamPractice();

        SpringApplication.run(GoalsApplications.class, args);
    }


}
