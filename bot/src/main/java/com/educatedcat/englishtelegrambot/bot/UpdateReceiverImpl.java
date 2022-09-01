package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.callback.*;
import com.educatedcat.englishtelegrambot.bot.command.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

@Component
@RequiredArgsConstructor
public class UpdateReceiverImpl implements UpdateReceiver {
	private final CommandHandler commandHandler;
	private final CallbackHandler callbackHandler;
	
	@Override
	public BotApiMethod<?> handle(Update update) {
		if (update.hasMessage() && update.getMessage().isCommand()) {
			return commandHandler.handle(update);
		} else if (update.hasCallbackQuery()) {
			return callbackHandler.handle(update);
		} else if (update.hasMessage() && !update.getMessage().isCommand()) {
			throw new NotCommandException();
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
}
