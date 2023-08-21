package ru.dream.checkingsleep;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title = "Sleep API", version = "2.0", description = "Dream Information"))
@SecurityScheme(name = "dreams-users", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class CheckingSleepApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckingSleepApplication.class, args);
	}

}
