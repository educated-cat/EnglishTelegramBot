package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.user.productivity.UserProductivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DontKnowWordActionButtonHandler extends AbstractWordActionButtonHandler {
	@Autowired
	public DontKnowWordActionButtonHandler(WordKeyboardFactory wordKeyboardFactory,
	                                       WordTextFactory wordTextFactory,
	                                       UserProductivityService userProductivityService) {
		super(WordActionType.DONT_KNOW, wordKeyboardFactory, wordTextFactory, userProductivityService);
	}
}
