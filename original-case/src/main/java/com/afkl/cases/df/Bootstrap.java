package com.afkl.cases.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.io.IOException;


@SpringBootApplication(exclude={org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class,SecurityAutoConfiguration.class})
@EnableOAuth2Client
@EnableAsync
public class Bootstrap extends SpringBootServletInitializer{

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder sab) {
		return sab.sources(Bootstrap.class);
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Bootstrap.class, args);
	}

}
