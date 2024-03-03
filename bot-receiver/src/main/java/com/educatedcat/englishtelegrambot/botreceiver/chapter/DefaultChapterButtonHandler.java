package com.educatedcat.englishtelegrambot.botreceiver.chapter;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import com.educatedcat.englishtelegrambot.botreceiver.button.AbstractButtonHandler;
import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.lesson.LessonKeyboardFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Locale;

@Component
public class DefaultChapterButtonHandler extends AbstractButtonHandler {
	private final LessonKeyboardFactory lessonKeyboardFactory;
	private final MessageSource messageSource;
	
	public DefaultChapterButtonHandler(LessonKeyboardFactory lessonKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.CHAPTER, ActionButtonType.NEXT);
		this.lessonKeyboardFactory = lessonKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return lessonKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(BotResponse response) {
		return messageSource.getMessage("page.lesson", null, Locale.ENGLISH);
	}
}
