package com.educatedcat.englishtelegrambot.bot.callback;

import com.educatedcat.englishtelegrambot.bot.button.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CallbackHandlerImpl implements CallbackHandler {
	private final EnumMap<MenuButtonType, CallbackButtonHandler> buttonMap;
	
	@Override
	@SneakyThrows
	public BotApiMethod<?> handle(BotResponse response) {
		return buttonMap.get(response.getCallback().button()).execute(response);
	}
}
