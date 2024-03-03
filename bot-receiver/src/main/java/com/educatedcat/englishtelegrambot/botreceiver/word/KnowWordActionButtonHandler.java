package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.user.productivity.UserProductivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KnowWordActionButtonHandler extends AbstractWordActionButtonHandler {
	@Autowired
	public KnowWordActionButtonHandler(WordKeyboardFactory wordKeyboardFactory,
	                                   WordTextFactory wordTextFactory,
	                                   UserProductivityService userProductivityService) {
		super(WordActionType.KNOW, wordKeyboardFactory, wordTextFactory, userProductivityService);
	}
}
