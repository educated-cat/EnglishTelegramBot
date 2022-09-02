package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.callback.*;
import com.educatedcat.englishtelegrambot.bot.command.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;

@Slf4j
@Component
public class EnglishTelegramBot extends TelegramLongPollingBot {
	
	private final UpdateReceiver updateReceiver;
	private final MessageSource messageSource;
	private final String botUsername;
	private final String botToken;
	
	@Autowired
	@SneakyThrows
	public EnglishTelegramBot(TelegramBotsApi telegramBotsApi, UpdateReceiver updateReceiver,
	                          MessageSource messageSource,
	                          @Value("${telegram.bot.username}") String botUsername,
	                          @Value("${telegram.bot.token}") String botToken) {
		this.updateReceiver = updateReceiver;
		this.messageSource = messageSource;
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
		try {
			sendMessage(updateReceiver.handle(update));
		} catch (UnknownCommandException | UnknownCallbackException | NotCommandException e) {
			final String chatId = update.hasMessage() ? update.getMessage().getChatId().toString()
			                                          : update.getCallbackQuery().getMessage().getChatId().toString();
			sendMessage(new SendMessage(chatId, getErrorMessage(e)));
		} catch (UnsupportedOperationException e) {
			log.error("Unsupported operation, update=" + update, e);
		}
	}
	
	private String getErrorMessage(Exception e) {
		if (e instanceof UnknownCommandException) {
			return messageSource.getMessage("bot.command.unknown", null, Locale.ENGLISH);
		} else if (e instanceof NotCommandException) {
			return messageSource.getMessage("bot.command.not", null, Locale.ENGLISH);
		} else {
			return messageSource.getMessage("bot.error.unexpected", null, Locale.ENGLISH);
		}
	}
	
	private void sendMessage(BotApiMethod<?> message) {
		try {
			this.execute(message);
		} catch (Exception e) {
			log.error("Unable to send message, message value={}", message.getClass().getSimpleName(), e);
		}
	}
}
