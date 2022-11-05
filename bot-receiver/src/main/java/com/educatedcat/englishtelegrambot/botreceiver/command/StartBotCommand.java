package com.educatedcat.englishtelegrambot.botreceiver.command;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import com.educatedcat.englishtelegrambot.botreceiver.start.*;
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
