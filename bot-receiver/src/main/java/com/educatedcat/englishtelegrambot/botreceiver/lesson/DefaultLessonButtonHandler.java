package com.educatedcat.englishtelegrambot.botreceiver.lesson;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import com.educatedcat.englishtelegrambot.botreceiver.word.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Component
public class DefaultLessonButtonHandler extends AbstractButtonHandler {
	private final WordKeyboardFactory wordKeyboardFactory;
	private final WordTextFactory wordTextFactory;
	
	public DefaultLessonButtonHandler(WordKeyboardFactory wordKeyboardFactory, WordTextFactory wordTextFactory) {
		super(MenuButtonType.LESSON, ActionButtonType.NEXT);
		this.wordKeyboardFactory = wordKeyboardFactory;
		this.wordTextFactory = wordTextFactory;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return wordKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(BotResponse response) {
		return wordTextFactory.buildText(response.getEntry());
	}
}
