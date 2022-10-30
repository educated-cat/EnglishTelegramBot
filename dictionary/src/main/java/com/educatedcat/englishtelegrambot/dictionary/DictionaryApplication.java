package com.educatedcat.englishtelegrambot.dictionary;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.annotation.*;

@EnableCaching
@SpringBootApplication
public class DictionaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DictionaryApplication.class, args);
	}
}
