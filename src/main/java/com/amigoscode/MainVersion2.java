package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class MainVersion2 {

    public static void main(String[] args) {
        SpringApplication.run(MainVersion2.class, args);
        System.out.println("Hello World!");
    }

    @GetMapping("/greet")
    public GreetResponse greet() {
        GreetResponse response = new GreetResponse(
                "Hello World!!",
                List.of("Java, Golang, Javascripts"),
                new Person("Gerardo","Aponte",39,500000)

        );
        return response;
    }

    record  Person(
            String name,
            String lastName,
            int Age,
            double saving

    ){}
    record GreetResponse(
        String greet,
        List<String> favProgrammingLanguages,
        Person person
    ){

    }
}
