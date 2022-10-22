package com.educatedcat.englishtelegrambot.bot.chapter;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;

import javax.annotation.*;
import java.util.*;

public class ChapterKeyboard extends BaseKeyboard {
	protected ChapterKeyboard(KeyboardEntryMapper keyboardEntryMapper, MessageSource messageSource,
	                          List<? extends ButtonMarker> buttons, CourseDto backButton) {
		super(keyboardEntryMapper, messageSource, buttons, backButton);
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
		return new KeyboardEntry(MenuButtonType.CHAPTER, ActionButtonType.PREVIOUS, backButton.id(), backButton.name());
	}
}
