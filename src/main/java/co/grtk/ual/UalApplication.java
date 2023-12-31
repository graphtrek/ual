package co.grtk.ual;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
@SecurityScheme(name = "ual", scheme = "Basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class UalApplication {

    public static void main(String[] args) {
        SpringApplication.run(UalApplication.class, args);
    }

}
