package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.user.productivity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class DontKnowWordActionButtonHandler extends AbstractWordActionButtonHandler {
	@Autowired
	public DontKnowWordActionButtonHandler(WordKeyboardFactory wordKeyboardFactory,
	                                       WordTextFactory wordTextFactory,
	                                       UserProductivityFacade userProductivityFacade) {
		super(WordActionType.DONT_KNOW, wordKeyboardFactory, wordTextFactory, userProductivityFacade);
	}
}
