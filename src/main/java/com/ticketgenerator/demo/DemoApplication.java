package com.ticketgenerator.demo;

import com.ticketgenerator.demo.service.StripService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        for(int i=0;i<10000;i++){
            System.out.println("i = "+i);
            StripService stripService = new StripService();
            stripService.firstStep();
        }

    }






}
