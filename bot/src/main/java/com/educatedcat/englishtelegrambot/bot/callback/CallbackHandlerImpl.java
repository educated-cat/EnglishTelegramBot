package com.educatedcat.englishtelegrambot.bot.callback;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.course.*;
import com.fasterxml.jackson.databind.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CallbackHandlerImpl implements CallbackHandler {
	private final EnumMap<MenuButtonType, ButtonHandler> buttonMap;
	private final ObjectMapper objectMapper;
	
	@Override
	@SneakyThrows
	public BotApiMethod<?> handle(Update update) {
		CallbackQuery callbackQuery = update.getCallbackQuery();
		ButtonCallback callback = objectMapper.readValue(callbackQuery.getData(), ButtonCallback.class);
		return buttonMap.get(callback.button()).execute(update);
	}
}
