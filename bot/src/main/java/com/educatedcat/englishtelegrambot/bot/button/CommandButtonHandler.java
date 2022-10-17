package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.response.*;
import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface CommandButtonHandler {
	BotApiMethod<?> execute(MessageBotResponse response);
}
