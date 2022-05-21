package com.educatedcat.englishtelegrambot.bot.telegram;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.api.objects.*;

@Component
public class EnglishTelegramBot extends TelegramLongPollingBot {
	
	private final UpdateReceiver updateReceiver;
	private final String botUsername;
	private final String botToken;
	
	@Autowired
	@SneakyThrows
	public EnglishTelegramBot(TelegramBotsApi telegramBotsApi, UpdateReceiver updateReceiver,
	                          @Value("${telegram.bot.username}") String botUsername,
	                          @Value("${telegram.bot.token}") String botToken) {
		this.updateReceiver = updateReceiver;
		this.botUsername = botUsername;
		this.botToken = botToken;
		
		telegramBotsApi.registerBot(this);
	}
	
	@Override
	public String getBotUsername() {
		return botUsername;
	}
	
	@Override
	public String getBotToken() {
		return botToken;
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		updateReceiver.handle(update);
	}
}
