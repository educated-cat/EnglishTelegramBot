package com.educatedcat.englishtelegrambot.botsender;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.netflix.eureka.*;

@EnableEurekaClient
@SpringBootApplication
public class BotSenderApplication {
	public static void main(String[] args) {
		SpringApplication.run(BotSenderApplication.class, args);
	}
}
