package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.course.*;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import com.educatedcat.englishtelegrambot.botreceiver.start.*;
import lombok.*;

import javax.annotation.*;
import java.util.*;

public class StatisticsKeyboard extends BaseKeyboard {
	protected StatisticsKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
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
		StartButton backButton = (StartButton) this.backButton;
		return new KeyboardEntry(MenuButtonType.COURSE, ActionButtonType.PREVIOUS, null,
		                         backButton.name(), backButtonIdType);
	}
}
