package com.github.madhav.SpringKafka.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner itemCommandLineRunner(ItemRepository repository) {
        return args -> {
            Item nike = new Item(
                    0L,
                    "Nike AirForce 1",
                    7400.0
            );
            Item adidas = new Item(
                    0L,
                    "Adidas OG T-Shirt",
                    1500.0
            );
            repository.saveAll(
                    Arrays.asList(nike, adidas)
            );
        };
    }
}
