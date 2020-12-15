package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-15 15:09
 **/

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class,args);
    }
}
