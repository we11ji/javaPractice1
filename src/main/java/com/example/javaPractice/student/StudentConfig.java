package com.example.javaPractice.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student bob = new Student(
                "Bob",
                LocalDate.of(2003, 1, 1),
                "email@email.com");
            Student bill = new Student(
                "Bill",
                LocalDate.of(2000, 1, 2),
                "email2@email.com");

            repository.saveAll(
                    List.of(bob,bill)
            );
        };
    }
}
