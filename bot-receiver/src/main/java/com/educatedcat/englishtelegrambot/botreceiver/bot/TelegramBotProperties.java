package com.educatedcat.englishtelegrambot.botreceiver.bot;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.util.*;

import java.net.*;

@Data
@Validated
@Component
@ConfigurationProperties("telegram.bot")
public class TelegramBotProperties {
	private Url url;
	
	/**
	 * Telegram bot token
	 */
	@NotBlank
	private String token;
	
	public String getFullApiUrl() {
		return UriComponentsBuilder.fromUriString(url.api.toString())
		                           .path(token)
		                           .build()
		                           .toUriString();
	}
	
	@Data
	@Validated
	public static class Url {
		@NotNull
		private URL api;
	}
}
