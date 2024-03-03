package com.educatedcat.englishtelegrambot.botreceiver.bot;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;

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
