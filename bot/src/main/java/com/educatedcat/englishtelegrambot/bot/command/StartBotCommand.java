package com.educatedcat.englishtelegrambot.bot.command;

import com.educatedcat.englishtelegrambot.bot.button.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

@Component
@RequiredArgsConstructor
public class StartBotCommand implements BotCommand {
	private final StartButtonHandler startButtonHandler;
	
	@Override
	public BotApiMethod<?> execute(Update update) {
		return startButtonHandler.execute(update);
	}
}
