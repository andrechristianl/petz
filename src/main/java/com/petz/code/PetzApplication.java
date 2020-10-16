package com.petz.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.petz.code.config.CoreConfiguration;

@SpringBootApplication
@ComponentScan({
    "com.petz.code.controller",
    "com.petz.code.service",
    "com.petz.code.repository"
})
public class PetzApplication extends CoreConfiguration{
    public static void main(String[] args) {
        SpringApplication.run(PetzApplication.class, args);
    }
}
