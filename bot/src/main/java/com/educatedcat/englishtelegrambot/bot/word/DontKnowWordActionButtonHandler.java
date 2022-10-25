package com.educatedcat.englishtelegrambot.bot.word;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class DontKnowWordActionButtonHandler extends AbstractWordActionButtonHandler {
	@Autowired
	public DontKnowWordActionButtonHandler(WordKeyboardFactory wordKeyboardFactory,
	                                       WordTextFactory wordTextFactory) {
		super(WordActionType.DONT_KNOW, wordKeyboardFactory, wordTextFactory);
	}
}
