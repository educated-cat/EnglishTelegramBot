package com.educatedcat.englishtelegrambot.user;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.netflix.eureka.*;

@EnableEurekaClient
@SpringBootApplication
public class UserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
}
