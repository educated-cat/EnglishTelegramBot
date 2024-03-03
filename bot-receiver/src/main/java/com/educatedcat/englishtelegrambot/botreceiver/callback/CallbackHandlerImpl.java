package com.educatedcat.englishtelegrambot.botreceiver.callback;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import com.educatedcat.englishtelegrambot.botreceiver.button.AbstractButtonHandler;
import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.ButtonHandlerKey;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.word.AbstractWordActionButtonHandler;
import com.educatedcat.englishtelegrambot.botreceiver.word.NoMoreWordsException;
import com.educatedcat.englishtelegrambot.botreceiver.word.WordActionType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CallbackHandlerImpl implements CallbackHandler {
	private final Map<ButtonHandlerKey, AbstractButtonHandler> buttonHandlerMap;
	private final Map<WordActionType, AbstractWordActionButtonHandler> wordActionsButtonHandlerMap;
	
	@Override
	@SneakyThrows
	public BotApiMethod<?> handle(BotResponse response) {
		try {
			if (response.getEntry().wordActionType() == null) {
				return buttonHandlerMap.get(new ButtonHandlerKey(response.getEntry().buttonType(),
				                                                 response.getEntry().actionType()))
				                       .handle(response);
			} else {
				return wordActionsButtonHandlerMap.get(response.getEntry().wordActionType()).handle(response);
			}
		} catch (NoMoreWordsException e) {
			return buttonHandlerMap.get(new ButtonHandlerKey(MenuButtonType.END_WORD, ActionButtonType.NEXT))
			                       .handle(response);
		}
	}
}
