package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.callback.*;
import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface CallbackButtonHandler {
	BotApiMethod<?> execute(BotResponse response);
}
