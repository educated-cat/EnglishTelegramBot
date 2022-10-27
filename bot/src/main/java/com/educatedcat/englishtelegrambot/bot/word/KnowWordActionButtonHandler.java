package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.user.productivity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class KnowWordActionButtonHandler extends AbstractWordActionButtonHandler {
	@Autowired
	public KnowWordActionButtonHandler(WordKeyboardFactory wordKeyboardFactory,
	                                   WordTextFactory wordTextFactory,
	                                   UserProductivityFacade userProductivityFacade) {
		super(WordActionType.KNOW, wordKeyboardFactory, wordTextFactory, userProductivityFacade);
	}
}
