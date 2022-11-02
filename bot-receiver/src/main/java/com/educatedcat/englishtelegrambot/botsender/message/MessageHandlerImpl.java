package com.educatedcat.englishtelegrambot.botsender.message;

import com.educatedcat.englishtelegrambot.botsender.bot.*;
import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.callback.*;
import com.educatedcat.englishtelegrambot.botsender.command.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import com.educatedcat.englishtelegrambot.botsender.user.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

@Component
public class MessageHandlerImpl implements MessageHandler {
	private final EnglishTelegramBot englishTelegramBot;
	private final CommandHandler commandHandler;
	private final CallbackHandler callbackHandler;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final UserService userService;
	
	// TODO: fix @Lazy injection
	public MessageHandlerImpl(@Lazy EnglishTelegramBot englishTelegramBot, CommandHandler commandHandler,
	                          CallbackHandler callbackHandler, KeyboardEntryMapper keyboardEntryMapper,
	                          UserService userService) {
		this.englishTelegramBot = englishTelegramBot;
		this.commandHandler = commandHandler;
		this.callbackHandler = callbackHandler;
		this.keyboardEntryMapper = keyboardEntryMapper;
		this.userService = userService;
	}
	
	@Override
	public void handle(Update update) {
		final BotApiMethod<?> result;
		if (update.hasMessage()) {
			var response = new BotResponse(update);
			userService.saveOrUpdate(new UserDto(response.chatId(), MenuButtonType.START, null));
			
			result = commandHandler.handle(response);
		} else if (update.hasCallbackQuery()) {
			final KeyboardEntry entry;
			try {
				entry = keyboardEntryMapper.deserialize(update.getCallbackQuery().getData(),
				                                        update.getCallbackQuery().getData());
			} catch (Exception e) {
				throw new UnknownCallbackException(e);
			}
			
			var response = new BotResponse(update, entry);
			userService.saveOrUpdate(
					new UserDto(response.chatId(), response.getEntry().buttonType(), response.getEntry().id()));
			result = callbackHandler.handle(response);
		} else {
			throw new UnsupportedOperationException();
		}
		englishTelegramBot.sendMessage(result);
	}
}
