package com.educatedcat.englishtelegrambot.bot.callback;

import com.educatedcat.englishtelegrambot.bot.button.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CallbackHandlerImpl implements CallbackHandler {
	private final Map<ButtonHandlerKey, AbstractButtonHandler> newButtonHandlerMap;
	
	@Override
	@SneakyThrows
	public BotApiMethod<?> handle(BotResponse response) {
		return newButtonHandlerMap
				.get(new ButtonHandlerKey(response.getEntry().buttonType(), response.getEntry().actionType()))
				.handle(response);
	}
}
