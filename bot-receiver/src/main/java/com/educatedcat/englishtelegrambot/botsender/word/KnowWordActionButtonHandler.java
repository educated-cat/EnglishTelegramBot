package com.educatedcat.englishtelegrambot.botsender.word;

import com.educatedcat.englishtelegrambot.botsender.user.productivity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class KnowWordActionButtonHandler extends AbstractWordActionButtonHandler {
	@Autowired
	public KnowWordActionButtonHandler(WordKeyboardFactory wordKeyboardFactory,
	                                   WordTextFactory wordTextFactory,
	                                   UserProductivityService userProductivityService) {
		super(WordActionType.KNOW, wordKeyboardFactory, wordTextFactory, userProductivityService);
	}
}
