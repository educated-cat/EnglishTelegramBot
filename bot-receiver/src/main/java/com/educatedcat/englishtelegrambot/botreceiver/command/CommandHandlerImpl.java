package com.educatedcat.englishtelegrambot.botreceiver.command;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.EnumMap;

@Service
@RequiredArgsConstructor
public class CommandHandlerImpl implements CommandHandler {
	private final EnumMap<BotCommandType, BotCommand> commandMap;
	
	@Override
	public BotApiMethod<?> handle(BotResponse response) {
		final BotCommandType commandType;
		try {
			commandType = BotCommandType.valueOf(response.getMessage().getText().substring(1).toUpperCase());
		} catch (NullPointerException | IllegalArgumentException e) {
			throw new UnknownCommandException(e);
		}
		return commandMap.get(commandType).execute(response);
	}
}
