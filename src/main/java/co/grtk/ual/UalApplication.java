package co.grtk.ual;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class UalApplication {

    public static void main(String[] args) {
        SpringApplication.run(UalApplication.class, args);
    }

}
