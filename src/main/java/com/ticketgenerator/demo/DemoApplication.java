package com.ticketgenerator.demo;

import com.ticketgenerator.demo.domain.Strip;
import com.ticketgenerator.demo.service.StripService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

import java.util.*;
import java.util.concurrent.*;


@SpringBootApplication
public class DemoApplication {

    volatile static List<Strip> stripList = new ArrayList<>();


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        Date start = new java.util.Date();

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Collection<Future<?>> futures = new LinkedList<Future<?>>();
        for(int i=0;i<10000;i++){
            futures.add(executorService.submit(()->{
                StripService stripService = new StripService();
                stripList.add(stripService.firstStep());
            }));

        }

        for(Future<?> future : futures){
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        StripService stripService = new StripService();

        List<Strip> newList = Collections.synchronizedList(stripList);


        for(Strip s : newList){
            stripService.showStrip(s);
        }


        Date end = new java.util.Date();
        System.out.println("duration = "+ (end.getTime()-start.getTime())/1);


    }






}
