package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.command.*;
import org.springframework.context.annotation.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@Configuration
public class MapConfig {
	@Bean()
	public EnumMap<BotCommandType, BotCommand> commandMap(BotCommand startBotCommand) {
		return new EnumMap<>(BotCommandType.class) {{
			put(BotCommandType.START, startBotCommand);
		}};
	}
	
	@Bean
	public Map<ButtonHandlerKey, AbstractButtonHandler> newButtonHandlerMap(List<AbstractButtonHandler> handlers) {
		return handlers.stream()
		               .collect(Collectors.toMap(
				               handler -> new ButtonHandlerKey(handler.getButtonType(), handler.getActionButtonType()),
				               Function.identity()));
	}
}
