package com.educatedcat.englishtelegrambot.bot.command;

import com.educatedcat.englishtelegrambot.bot.callback.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;

import java.util.*;

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
