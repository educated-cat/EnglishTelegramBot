package com.educatedcat.englishtelegrambot.botreceiver.message;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.callback.CallbackHandler;
import com.educatedcat.englishtelegrambot.botreceiver.callback.UnknownCallbackException;
import com.educatedcat.englishtelegrambot.botreceiver.command.CommandHandler;
import com.educatedcat.englishtelegrambot.botreceiver.command.NotCommandException;
import com.educatedcat.englishtelegrambot.botreceiver.command.UnknownCommandException;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import com.educatedcat.englishtelegrambot.botreceiver.user.UserDto;
import com.educatedcat.englishtelegrambot.botreceiver.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {
	private final MessageSender messageSender;
	private final CommandHandler commandHandler;
	private final CallbackHandler callbackHandler;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final UserService userService;
	
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
}
