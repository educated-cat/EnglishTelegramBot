package com.educatedcat.englishtelegrambot.botreceiver.command;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import com.educatedcat.englishtelegrambot.botreceiver.start.DefaultStartButtonHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class StartBotCommand implements BotCommand {
	private final DefaultStartButtonHandler startButtonHandler;
	
	@Override
	public BotApiMethod<?> execute(BotResponse response) {
		return startButtonHandler.handle(response);
	}
}
