package org.example.eurrekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurrekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurrekaServerApplication.class, args);
    }
}