package com.educatedcat.englishtelegrambot.botsender.message;

import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;

@Component
public class EditMessageTestMessageSender extends AbstractMessageSender {
	public EditMessageTestMessageSender(WebClient botWebClient) {
		super(botWebClient);
	}
	
	@Override
	Class<? extends BotApiMethod<?>> getApiMethod() {
		return EditMessageText.class;
	}
	
	@Override
	String getApiPath() {
		return "/editMessageText";
	}
}
