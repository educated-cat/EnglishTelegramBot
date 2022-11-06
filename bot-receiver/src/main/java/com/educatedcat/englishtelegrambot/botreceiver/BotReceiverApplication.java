package com.educatedcat.englishtelegrambot.botreceiver;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.netflix.eureka.*;
import org.springframework.scheduling.annotation.*;

@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
public class BotReceiverApplication {
	public static void main(String[] args) {
		SpringApplication.run(BotReceiverApplication.class, args);
	}
}
