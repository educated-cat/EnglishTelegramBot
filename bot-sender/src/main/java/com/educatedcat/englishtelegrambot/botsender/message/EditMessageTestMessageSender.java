package com.educatedcat.englishtelegrambot.botsender.message;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

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
