package com.educatedcat.englishtelegrambot.botsender.bot;

import com.educatedcat.englishtelegrambot.botsender.callback.*;
import com.educatedcat.englishtelegrambot.botsender.command.*;
import com.educatedcat.englishtelegrambot.botsender.message.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.*;
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
@RequiredArgsConstructor
public class EnglishTelegramBot extends TelegramLongPollingBot implements InitializingBean {
	private final TelegramBotsApi telegramBotsApi;
	private final TelegramBotProperties telegramBotProperties;
	private final MessageHandler messageHandler;
	private final MessageSource messageSource;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		telegramBotsApi.registerBot(this);
	}
	
	@Override
	public String getBotUsername() {
		return telegramBotProperties.getUsername();
	}
	
	@Override
	public String getBotToken() {
		return telegramBotProperties.getToken();
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		try {
			messageHandler.handle(update);
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
	
	// TODO: move to another bean
	public void sendMessage(BotApiMethod<?> message) {
		try {
			this.execute(message);
		} catch (Exception e) {
			log.error("Unable to send message, message value={}", message.getClass().getSimpleName(), e);
		}
	}
}
