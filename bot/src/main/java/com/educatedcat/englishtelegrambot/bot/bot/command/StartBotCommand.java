package com.educatedcat.englishtelegrambot.bot.bot.command;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;

@Component
public class StartBotCommand implements BotCommand {
	private final MessageSource messageSource;
	
	@Autowired
	public StartBotCommand(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
	public BotApiMethod<Message> execute(Update update) {
		return new SendMessage(update.getMessage().getChatId().toString(),
		                       messageSource.getMessage("bot.command.start", null, Locale.ENGLISH));
	}
}
