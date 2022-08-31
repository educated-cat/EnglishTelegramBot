package com.educatedcat.englishtelegrambot.bot.callback;

import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

public interface CallbackHandler {
	BotApiMethod<?> handle(Update update);
}
