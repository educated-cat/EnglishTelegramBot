package com.educatedcat.englishtelegrambot.bot.lesson;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;

import javax.annotation.*;
import java.util.*;

public class LessonKeyboard extends BaseKeyboard {
	protected LessonKeyboard(KeyboardEntryMapper keyboardEntryMapper, MessageSource messageSource,
	                         List<? extends ButtonMarker> buttons, ChapterDto backButton) {
		super(keyboardEntryMapper, messageSource, buttons, backButton);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (LessonDto) buttonMarker)
		              .map(button -> new KeyboardEntry(MenuButtonType.LESSON, button.id(), button.name()))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	@Nullable
	protected KeyboardEntry backButton() {
		ChapterDto backButton = (ChapterDto) this.backButton;
		return new KeyboardEntry(MenuButtonType.LESSON, ActionButtonType.PREVIOUS, backButton.id(), backButton.name());
	}
}
