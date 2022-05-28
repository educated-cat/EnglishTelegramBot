package com.educatedcat.englishtelegrambot.bot.bot;

import com.educatedcat.englishtelegrambot.bot.bot.command.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;

@Component
public class UpdateReceiverImpl implements UpdateReceiver {
	private final EnumMap<BotCommandType, BotCommand> commandMap;
	
	@Autowired
	public UpdateReceiverImpl(StartBotCommand startBotCommand) {
		commandMap = new EnumMap<>(BotCommandType.class) {{
			put(BotCommandType.START, startBotCommand);
		}};
	}
	
	@Override
	public BotApiMethod<Message> handle(Update update) {
		if (update.getMessage().isCommand()) {
			final BotCommandType commandType = BotCommandType.valueOf(
					update.getMessage().getText().substring(1).toUpperCase());
			return commandMap.get(commandType).execute(update);
		} else {
			throw new UnsupportedOperationException();
		}
	}
}
