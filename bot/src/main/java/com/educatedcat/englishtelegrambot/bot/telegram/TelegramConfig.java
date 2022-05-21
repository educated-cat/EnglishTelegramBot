package com.educatedcat.englishtelegrambot.bot.telegram;

import lombok.*;
import org.springframework.context.annotation.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.updatesreceivers.*;

@Configuration
public class TelegramConfig {
	@Bean
	@SneakyThrows
	public TelegramBotsApi telegramBotsApi() {
		return new TelegramBotsApi(DefaultBotSession.class);
	}
}
