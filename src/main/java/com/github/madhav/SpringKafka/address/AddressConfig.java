package com.github.madhav.SpringKafka.address;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AddressConfig {

    @Bean
    CommandLineRunner addressCommandLineRunner(AddressRepository repository) {
        return args -> {
            Address address1 = new Address(
                    "Ted Jones",
                    "Flat 21, Building 7",
                    "Bandra",
                    "Mumbai",
                    "Maharashtra",
                    "111000",
                    "8291289121"
            );
            Address address2 = new Address(
                    "Freddy Fom",
                    "Flat 101, Building 2",
                    "Saket",
                    "New Delhi",
                    "Delhi",
                    "100100",
                    "9293289121"
            );

            repository.saveAll(
                    Arrays.asList(address1, address2)
            );
        };
    }
}
