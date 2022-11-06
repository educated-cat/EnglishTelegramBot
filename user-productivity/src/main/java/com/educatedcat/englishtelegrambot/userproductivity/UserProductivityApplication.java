package com.educatedcat.englishtelegrambot.userproductivity;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.netflix.eureka.*;

@EnableEurekaClient
@SpringBootApplication
public class UserProductivityApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserProductivityApplication.class, args);
	}
	
}
