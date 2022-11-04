package com.educatedcat.englishtelegrambot.botsender.message;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;

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
