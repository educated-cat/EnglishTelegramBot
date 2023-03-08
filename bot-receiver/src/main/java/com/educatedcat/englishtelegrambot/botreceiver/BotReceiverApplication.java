package com.educatedcat.englishtelegrambot.botreceiver;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.scheduling.annotation.*;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {
		"com.educatedcat.englishtelegrambot.botreceiver",
		"com.educatedcat.englishtelegrambot.kafkalib"
})
public class BotReceiverApplication {
	public static void main(String[] args) {
		SpringApplication.run(BotReceiverApplication.class, args);
	}
}
