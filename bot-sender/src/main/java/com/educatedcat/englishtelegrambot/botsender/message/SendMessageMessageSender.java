package com.educatedcat.englishtelegrambot.botsender.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class SendMessageMessageSender extends AbstractMessageSender {
	@Autowired
	public SendMessageMessageSender(WebClient botWebClient) {
		super(botWebClient);
	}
	
	@Override
	Class<? extends BotApiMethod<?>> getApiMethod() {
		return SendMessage.class;
	}
	
	@Override
	String getApiPath() {
		return "/sendMessage";
	}
}
