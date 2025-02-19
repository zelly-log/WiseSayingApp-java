package com.ll.wisesayingappjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class WiseSayingAppJavaApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WiseSayingAppJavaApplication.class, args);

        // App 실행
        App app = context.getBean(App.class);
        app.run();

    }



}
