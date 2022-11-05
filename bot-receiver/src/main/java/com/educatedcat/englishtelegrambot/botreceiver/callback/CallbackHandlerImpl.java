package com.educatedcat.englishtelegrambot.botreceiver.callback;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.word.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;

import java.util.*;

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
