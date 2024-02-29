package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.course.CourseDto;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import com.educatedcat.englishtelegrambot.botreceiver.start.StartButton;
import jakarta.annotation.Nullable;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;

import java.util.List;

public class StatisticsKeyboard extends BaseKeyboard {
	protected StatisticsKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
	                             ButtonMarker backButton, MenuButtonType backButtonIdType, MessageSource messageSource) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType, messageSource);
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
		StartButton backButton = (StartButton) this.backButton;
		return new KeyboardEntry(MenuButtonType.COURSE, ActionButtonType.PREVIOUS, null,
		                         backButton.name(), backButtonIdType);
	}
}
