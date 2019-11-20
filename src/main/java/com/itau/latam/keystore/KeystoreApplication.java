package com.itau.latam.keystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
    "com.itau.latam.config",
    "com.itau.latam.keystore.controller",
    "com.itau.latam.keystore.service",
    "com.itau.latam.keystore.repository"
})
public class KeystoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeystoreApplication.class, args);
    }
    
}
