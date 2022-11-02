package com.educatedcat.englishtelegrambot.botsender.lesson;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.chapter.*;
import com.educatedcat.englishtelegrambot.botsender.dictionary.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import lombok.*;

import javax.annotation.*;
import java.util.*;

public class LessonKeyboard extends BaseKeyboard {
	protected LessonKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
	                         ChapterDto backButton, MenuButtonType backButtonIdType) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType);
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
		return new KeyboardEntry(MenuButtonType.LESSON, ActionButtonType.PREVIOUS, backButton.id(), backButton.name(),
		                         backButtonIdType);
	}
}
