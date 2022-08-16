package com.educatedcat.englishtelegrambot.bot.command;

import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

@FunctionalInterface
public interface BotCommand {
	BotApiMethod<?> execute(Update update);
}
