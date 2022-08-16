package com.educatedcat.englishtelegrambot.bot;

import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

interface UpdateReceiver {
	BotApiMethod<?> handle(Update update);
}
