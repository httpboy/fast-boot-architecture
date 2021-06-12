package com.fast.architecturedesignmode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class ArchitectureDesignModeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectureDesignModeApplication.class, args);
    }

    @Bean
//    @Scope("prototype")
    @Scope("singleton")
    @Lazy
    public Object createBean(){
       return new Object();
    }


}
