package com.example.foodstorecomparison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FoodstoreComparisonApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodstoreComparisonApplication.class, args);
    }

}
