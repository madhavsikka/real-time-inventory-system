package com.github.madhav.SpringKafka.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner customerCommandLineRunner(CustomerRepository repository) {
        return args -> {
//            Customer mariam = new Customer(
//                    "Mariam",
//                    "Mack",
//                    "mariam@mail.com",
//                    "8291289121"
//            );
//            Customer alex = new Customer(
//                    "Alex",
//                    "Wing",
//                    "alex@mail.com",
//                    "9191239121"
//            );
//            repository.saveAll(
//                    Arrays.asList(mariam, alex)
//            );
        };
    }
}
