package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.user.productivity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class DontKnowWordActionButtonHandler extends AbstractWordActionButtonHandler {
	@Autowired
	public DontKnowWordActionButtonHandler(WordKeyboardFactory wordKeyboardFactory,
	                                       WordTextFactory wordTextFactory,
	                                       UserProductivityService userProductivityService) {
		super(WordActionType.DONT_KNOW, wordKeyboardFactory, wordTextFactory, userProductivityService);
	}
}
