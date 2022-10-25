package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import com.educatedcat.englishtelegrambot.bot.lesson.*;
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
