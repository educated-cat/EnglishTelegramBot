package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class StartButtonHandler extends AbstractButtonHandler {
	private final StartKeyboard startKeyboard;
	private final MessageSource messageSource;
	
	@Autowired
	public StartButtonHandler(StartKeyboard startKeyboard, MessageSource messageSource) {
		this.startKeyboard = startKeyboard;
		this.messageSource = messageSource;
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.start", null, Locale.ENGLISH);
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return startKeyboard;
	}
}
