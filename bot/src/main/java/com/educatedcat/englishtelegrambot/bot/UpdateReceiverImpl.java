package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.command.*;
import com.educatedcat.englishtelegrambot.bot.course.*;
import com.fasterxml.jackson.databind.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;

@Component
public class UpdateReceiverImpl implements UpdateReceiver {
	private final EnumMap<BotCommandType, BotCommand> commandMap;
	private final EnumMap<MenuButtonType, ButtonHandler> buttonMap;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public UpdateReceiverImpl(EnumMap<BotCommandType, BotCommand> commandMap,
	                          EnumMap<MenuButtonType, ButtonHandler> buttonMap, ObjectMapper objectMapper) {
		this.commandMap = commandMap;
		this.buttonMap = buttonMap;
		this.objectMapper = objectMapper;
	}
	
	@Override
	public BotApiMethod<?> handle(Update update) {
		if (update.hasMessage() && update.getMessage().isCommand()) {
			final BotCommandType commandType = BotCommandType.valueOf(
					update.getMessage().getText().substring(1).toUpperCase());
			return commandMap.get(commandType).execute(update);
		} else if (update.hasCallbackQuery()) {
			return handleCallbackQuery(update);
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	@SneakyThrows
	private BotApiMethod<?> handleCallbackQuery(Update update) {
		CallbackQuery callbackQuery = update.getCallbackQuery();
		ButtonCallback callback = objectMapper.readValue(callbackQuery.getData(), ButtonCallback.class);
		return buttonMap.get(callback.button()).execute(update);
	}
	
}
