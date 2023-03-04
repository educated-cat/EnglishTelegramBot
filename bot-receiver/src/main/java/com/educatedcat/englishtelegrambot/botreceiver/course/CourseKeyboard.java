package com.educatedcat.englishtelegrambot.botreceiver.course;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import jakarta.annotation.*;
import lombok.*;

import java.util.*;

public class CourseKeyboard extends BaseKeyboard {
	protected CourseKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
	                         ButtonMarker backButton, MenuButtonType backButtonIdType) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType);
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
		                         backButton.name(), backButtonIdType);
	}
}
