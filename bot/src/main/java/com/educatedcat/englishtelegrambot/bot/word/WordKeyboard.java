package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;

import javax.annotation.*;
import java.util.*;

public class WordKeyboard extends BaseKeyboard {
	protected WordKeyboard(KeyboardEntryMapper keyboardEntryMapper, MessageSource messageSource,
	                       List<? extends ButtonMarker> buttons, LessonDto backButton,
	                       MenuButtonType backButtonIdType) {
		super(keyboardEntryMapper, messageSource, buttons, backButton, backButtonIdType);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (WordDto) buttonMarker)
		              .map(button -> new KeyboardEntry(MenuButtonType.WORD, button.id(), button.name()))
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
