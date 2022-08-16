package com.educatedcat.englishtelegrambot.bot.button;

import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

@FunctionalInterface
public interface ButtonHandler {
	BotApiMethod<?> execute(Update update);
}
