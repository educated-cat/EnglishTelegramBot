package com.educatedcat.englishtelegrambot.botreceiver.command;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface BotCommand {
	BotApiMethod<?> execute(BotResponse response);
}
