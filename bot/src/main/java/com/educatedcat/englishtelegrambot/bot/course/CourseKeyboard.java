package com.educatedcat.englishtelegrambot.bot.course;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;

import javax.annotation.*;
import java.util.*;

public class CourseKeyboard extends BaseKeyboard {
	protected CourseKeyboard(KeyboardEntryMapper keyboardEntryMapper, MessageSource messageSource,
	                         List<? extends ButtonMarker> buttons, ButtonMarker backButton) {
		super(keyboardEntryMapper, messageSource, buttons, backButton);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (CourseDto) buttonMarker)
		              .map(button -> new KeyboardEntry(MenuButtonType.COURSE, button.id(), button.name()))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	@Nullable
	protected KeyboardEntry backButton() {
		CourseDto backButton = (CourseDto) this.backButton;
		return new KeyboardEntry(MenuButtonType.COURSE, ActionButtonType.PREVIOUS, backButton.id(),
		                         backButton.name());
	}
}
