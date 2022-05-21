package com.educatedcat.englishtelegrambot.bot.telegram;

import org.telegram.telegrambots.meta.api.objects.*;

interface UpdateReceiver {
	void handle(Update update);
}
