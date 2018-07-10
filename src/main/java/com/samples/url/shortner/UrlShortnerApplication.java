package com.samples.url.shortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.delivery.request"})
@EntityScan("com.delivery.domain")
public class UrlShortnerApplication {
	public static void main(String[] args) {
		SpringApplication.run(UrlShortnerApplication.class, args);
	}
}
