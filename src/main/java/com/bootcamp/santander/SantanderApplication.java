package com.bootcamp.santander;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SantanderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantanderApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application.description}") String appDescription
								,@Value("${application.version}") String appVersion
								,@Value("${application.termsOfService}") String appTermsOfService
								,@Value("${application.license.name}") String licenseName
								,@Value("${application.license.url}") String licenseUrl) {

		return new OpenAPI().info(new Info()
				.title(appDescription)
				.version(appVersion)
				.termsOfService(appTermsOfService)
				.license(new License()
						.name(licenseName)
						.url(licenseUrl)));
	}
}
