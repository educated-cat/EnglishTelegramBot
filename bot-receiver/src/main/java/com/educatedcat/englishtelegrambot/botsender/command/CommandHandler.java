package com.educatedcat.englishtelegrambot.botsender.command;

import com.educatedcat.englishtelegrambot.botsender.bot.*;
import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface CommandHandler {
	BotApiMethod<?> handle(BotResponse response);
}
