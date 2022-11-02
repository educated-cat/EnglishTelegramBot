package com.educatedcat.englishtelegrambot.botsender.bot;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import javax.validation.constraints.*;

@Data
@Validated
@Component
@ConfigurationProperties("telegram.bot")
public class TelegramBotProperties {
	/**
	 * Telegram bot username
	 */
	@NotBlank
	private String username;
	
	/**
	 * Telegram bot token
	 */
	@NotBlank
	private String token;
}
