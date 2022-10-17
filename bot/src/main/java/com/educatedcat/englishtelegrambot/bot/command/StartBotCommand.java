package com.educatedcat.englishtelegrambot.bot.command;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.response.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;

@Component
@RequiredArgsConstructor
public class StartBotCommand implements BotCommand {
	private final StartButtonHandler startButtonHandler;
	
	@Override
	public BotApiMethod<?> execute(MessageBotResponse response) {
		return startButtonHandler.execute(response);
	}
}
