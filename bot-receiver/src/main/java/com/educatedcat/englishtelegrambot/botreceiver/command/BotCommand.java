package com.educatedcat.englishtelegrambot.botreceiver.command;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@FunctionalInterface
public interface BotCommand {
	BotApiMethod<?> execute(BotResponse response);
}
