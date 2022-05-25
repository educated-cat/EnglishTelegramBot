package com.educatedcat.englishtelegrambot.bot.bot;

import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

interface UpdateReceiver {
	BotApiMethod<Message> handle(Update update);
}
