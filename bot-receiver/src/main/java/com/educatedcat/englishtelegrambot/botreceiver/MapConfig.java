package com.educatedcat.englishtelegrambot.botreceiver;

import com.educatedcat.englishtelegrambot.botreceiver.button.AbstractButtonHandler;
import com.educatedcat.englishtelegrambot.botreceiver.button.ButtonHandlerKey;
import com.educatedcat.englishtelegrambot.botreceiver.command.BotCommand;
import com.educatedcat.englishtelegrambot.botreceiver.command.BotCommandType;
import com.educatedcat.englishtelegrambot.botreceiver.word.AbstractWordActionButtonHandler;
import com.educatedcat.englishtelegrambot.botreceiver.word.WordActionType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
