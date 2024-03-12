package com.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfiguration {
	@Bean
	public OpenAPI usersMicroserviceOpenAPI() {

		Server localhost = new Server();
		localhost.setUrl("http://localhost:8080");
		localhost.setDescription("Development environment");

		Contact contact = new Contact();
		contact.setEmail("fireflink@mail.in");
		contact.setName("Student Management Application");
		contact.setUrl("https://sma.in");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("SMA App RESTful Web Service documentation").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage Application.")
				.termsOfService("https://sma.in/terms").license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(localhost));
	}
}
