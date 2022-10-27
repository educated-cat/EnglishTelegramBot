package com.educatedcat.englishtelegrambot.bot.word.end;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import com.educatedcat.englishtelegrambot.bot.lesson.*;
import lombok.*;

import javax.annotation.*;
import java.util.*;

public class EndWordKeyboard extends BaseKeyboard {
	protected EndWordKeyboard(KeyboardEntryMapper keyboardEntryMapper, LessonDto backButton,
	                          MenuButtonType backButtonIdType) {
		super(keyboardEntryMapper, Collections.emptyList(), backButton, backButtonIdType);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return Collections.emptyList();
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
