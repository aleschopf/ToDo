package tech.lastbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"tech.lastbox.entities", "tech.lastbox.jwt"})
public class ToDoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class);
    }
}