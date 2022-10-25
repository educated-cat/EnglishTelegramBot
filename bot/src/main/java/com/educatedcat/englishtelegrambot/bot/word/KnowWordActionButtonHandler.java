package com.educatedcat.englishtelegrambot.bot.word;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class KnowWordActionButtonHandler extends AbstractWordActionButtonHandler {
	@Autowired
	public KnowWordActionButtonHandler(WordKeyboardFactory wordKeyboardFactory,
	                                   WordTextFactory wordTextFactory) {
		super(WordActionType.KNOW, wordKeyboardFactory, wordTextFactory);
	}
}
