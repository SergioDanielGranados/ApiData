package org.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.data")
public class ApiDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDataApplication.class, args);
    }

}
