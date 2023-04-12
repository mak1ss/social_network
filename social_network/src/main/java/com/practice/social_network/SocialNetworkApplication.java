package com.practice.social_network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude =
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
@EnableJpaRepositories
public class SocialNetworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

}
