package com.educatedcat.englishtelegrambot.botreceiver.message;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.callback.*;
import com.educatedcat.englishtelegrambot.botreceiver.command.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import com.educatedcat.englishtelegrambot.botreceiver.user.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {
	private final MessageSender messageSender;
	private final CommandHandler commandHandler;
	private final CallbackHandler callbackHandler;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final UserService userService;
	private final MessageSource messageSource;
	
	@Override
	public void handle(Update update) {
		try {
			final BotApiMethod<?> result;
			if (update.hasMessage()) {
				var response = new BotResponse(update);
				userService.saveOrUpdate(new UserDto(response.chatId(), MenuButtonType.START, null));
				
				result = commandHandler.handle(response);
			} else if (update.hasCallbackQuery()) {
				final KeyboardEntry entry;
				try {
					entry = keyboardEntryMapper.deserialize(update.getCallbackQuery().getData(),
					                                        update.getCallbackQuery().getData());
				} catch (Exception e) {
					throw new UnknownCallbackException(e);
				}
				
				var response = new BotResponse(update, entry);
				userService.saveOrUpdate(
						new UserDto(response.chatId(), response.getEntry().buttonType(), response.getEntry().id()));
				result = callbackHandler.handle(response);
			} else {
				throw new UnsupportedOperationException();
			}
			messageSender.send(result);
		} catch (UnknownCommandException | UnknownCallbackException | NotCommandException e) {
			final String chatId = update.hasMessage() ? update.getMessage().getChatId().toString()
			                                          : update.getCallbackQuery().getMessage().getChatId().toString();
			// TODO: send error message
			log.error("User ID=" + chatId, e);
		} catch (UnsupportedOperationException e) {
			log.error("Unsupported operation, update=" + update, e);
		}
	}
	
	private String getErrorMessage(Exception e) {
		if (e instanceof UnknownCommandException) {
			return messageSource.getMessage("bot.command.unknown", null, Locale.ENGLISH);
		} else if (e instanceof NotCommandException) {
			return messageSource.getMessage("bot.command.not", null, Locale.ENGLISH);
		} else {
			return messageSource.getMessage("bot.error.unexpected", null, Locale.ENGLISH);
		}
	}
}
