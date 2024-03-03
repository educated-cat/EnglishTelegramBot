package com.educatedcat.englishtelegrambot.botreceiver.chapter;

import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.course.CourseDto;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import jakarta.annotation.Nullable;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;

import java.util.List;

public class ChapterKeyboard extends BaseKeyboard {
	protected ChapterKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
	                          CourseDto backButton, MenuButtonType backButtonIdType, MessageSource messageSource) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType, messageSource);
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
