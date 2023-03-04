package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import com.educatedcat.englishtelegrambot.botreceiver.lesson.*;
import jakarta.annotation.*;
import lombok.*;
import org.springframework.util.*;

import java.util.*;

public class WordKeyboard extends BaseKeyboard {
	protected WordKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<WordAction> buttons,
	                       LessonDto backButton, MenuButtonType backButtonIdType) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (WordAction) buttonMarker)
		              .map(button -> new KeyboardEntry(MenuButtonType.WORD, button.id(),
		                                               StringUtils.capitalize(button.actionType().name().toLowerCase()),
		                                               button.actionType()))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	@Nullable
	protected KeyboardEntry backButton() {
		LessonDto backButton = (LessonDto) this.backButton;
		return new KeyboardEntry(MenuButtonType.WORD, ActionButtonType.PREVIOUS, backButton.id(), backButton.name(),
		                         backButtonIdType);
	}
}
