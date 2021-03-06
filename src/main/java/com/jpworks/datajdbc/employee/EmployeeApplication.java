package com.jpworks.datajdbc.employee;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class EmployeeApplication implements CommandLineRunner {
	@Autowired
	BuildProperties buildProperties;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(BuildProperties buildProperties) {
		var contact = new Contact();
		contact.setEmail("joaquin.ponte@gmail.com");
		contact.setName("Joaquin Ponte");

		return new OpenAPI()
				.components(new Components().addSecuritySchemes("basicScheme",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.info(new Info().title("Employee API").version(buildProperties.getVersion()).description(
						"Employee services. The endpoint in AWS development: https://tbd/tbd")
						.termsOfService("https://www.jpworks.com")
						.contact(contact)
						.license(new License().name("Apache License 2.0").url("http://www.apache.org/licenses")));
	}

	@Override
	public void run(String... args) {
		log.info("Application version: {}:{}:{}:{}, {}", buildProperties.getGroup(), buildProperties.getArtifact(), buildProperties.getName(), buildProperties.getVersion(), buildProperties.getTime());
	}
}
