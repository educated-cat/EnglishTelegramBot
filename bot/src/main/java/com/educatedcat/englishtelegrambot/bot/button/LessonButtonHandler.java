package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Component
@RequiredArgsConstructor
public class LessonButtonHandler extends AbstractButtonHandler {
	private final WordKeyboard wordKeyboard;
	
	@Override
	protected String getText() {
		return "...";
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return wordKeyboard;
	}
}
