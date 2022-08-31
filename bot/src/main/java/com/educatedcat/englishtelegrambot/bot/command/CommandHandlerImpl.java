package com.educatedcat.englishtelegrambot.bot.command;

import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommandHandlerImpl implements CommandHandler {
	private final EnumMap<BotCommandType, BotCommand> commandMap;
	
	@Override
	public BotApiMethod<?> handle(Update update) {
		final BotCommandType commandType = BotCommandType.valueOf(
				update.getMessage().getText().substring(1).toUpperCase());
		return commandMap.get(commandType).execute(update);
	}
}
