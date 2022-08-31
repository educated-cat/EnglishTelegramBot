package com.educatedcat.englishtelegrambot.bot.command;

import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

public interface CommandHandler {
	BotApiMethod<?> handle(Update update);
}
