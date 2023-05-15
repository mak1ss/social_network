package com.practice.social_network;

import ch.qos.logback.classic.util.ContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude =
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
@EnableJpaRepositories
@EnableJpaAuditing
@PropertySource("classpath:/local_db_creds.properties")
public class SocialNetworkApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        System.out.println("Init");
        SpringApplication.run(SocialNetworkApplication.class, args);

    }

}
