package com.educatedcat.englishtelegrambot.botsender.command;

import com.educatedcat.englishtelegrambot.botsender.bot.*;
import com.educatedcat.englishtelegrambot.botsender.start.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;

@Component
@RequiredArgsConstructor
public class StartBotCommand implements BotCommand {
	private final DefaultStartButtonHandler startButtonHandler;
	
	@Override
	public BotApiMethod<?> execute(BotResponse response) {
		return startButtonHandler.handle(response);
	}
}
