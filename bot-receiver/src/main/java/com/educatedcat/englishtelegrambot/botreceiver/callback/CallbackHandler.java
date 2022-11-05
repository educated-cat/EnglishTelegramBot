package com.educatedcat.englishtelegrambot.botreceiver.callback;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface CallbackHandler {
	BotApiMethod<?> handle(BotResponse response);
}
