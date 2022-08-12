package com.ticketgenerator.demo;

import com.ticketgenerator.demo.service.StripService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        StripService stripService = new StripService();
        stripService.firstStep();

    }






}
