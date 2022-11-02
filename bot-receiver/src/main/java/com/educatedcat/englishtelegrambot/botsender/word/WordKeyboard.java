package com.educatedcat.englishtelegrambot.botsender.word;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import com.educatedcat.englishtelegrambot.botsender.lesson.*;
import lombok.*;
import org.springframework.util.*;

import javax.annotation.*;
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
