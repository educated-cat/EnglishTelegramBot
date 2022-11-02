package com.educatedcat.englishtelegrambot.botsender.chapter;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.course.*;
import com.educatedcat.englishtelegrambot.botsender.dictionary.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import lombok.*;

import javax.annotation.*;
import java.util.*;

public class ChapterKeyboard extends BaseKeyboard {
	protected ChapterKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
	                          CourseDto backButton, MenuButtonType backButtonIdType) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (ChapterDto) buttonMarker)
		              .map(button -> new KeyboardEntry(MenuButtonType.CHAPTER, button.id(), button.name()))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	@Nullable
	protected KeyboardEntry backButton() {
		CourseDto backButton = (CourseDto) this.backButton;
		return new KeyboardEntry(MenuButtonType.CHAPTER, ActionButtonType.PREVIOUS, backButton.id(), backButton.name(),
		                         backButtonIdType);
	}
}
