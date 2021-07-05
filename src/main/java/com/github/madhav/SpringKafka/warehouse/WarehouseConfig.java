package com.github.madhav.SpringKafka.warehouse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class WarehouseConfig {
    @Bean
    CommandLineRunner warehouseCommandLineRunner(WarehouseRepository repository) {
        return args -> {
            Warehouse delhi = new Warehouse(
                    "Delhi"
            );
            Warehouse mumbai = new Warehouse(
                    "Mumbai"
            );
            repository.saveAll(
                    Arrays.asList(delhi, mumbai)
            );
        };
    }
}
