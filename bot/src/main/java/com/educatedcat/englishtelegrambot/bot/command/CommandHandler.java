package com.educatedcat.englishtelegrambot.bot.command;

import com.educatedcat.englishtelegrambot.bot.callback.*;
import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface CommandHandler {
	BotApiMethod<?> handle(BotResponse response);
}
