package com.educatedcat.englishtelegrambot.botreceiver;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.command.*;
import com.educatedcat.englishtelegrambot.botreceiver.word.*;
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
	public Map<ButtonHandlerKey, AbstractButtonHandler> buttonHandlerMap(List<AbstractButtonHandler> handlers) {
		return handlers.stream()
		               .filter(handler -> !(handler instanceof AbstractWordActionButtonHandler))
		               .collect(Collectors.toMap(
				               handler -> new ButtonHandlerKey(handler.getButtonType(), handler.getActionButtonType()),
				               Function.identity()));
	}
	
	@Bean
	public Map<WordActionType, AbstractWordActionButtonHandler> wordActionsButtonHandlerMap(
			List<AbstractWordActionButtonHandler> handlers) {
		return handlers.stream()
		               .collect(Collectors.toMap(AbstractWordActionButtonHandler::getWordActionType,
		                                         Function.identity()));
	}
}
