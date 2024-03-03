package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import com.educatedcat.englishtelegrambot.botreceiver.lesson.LessonDto;
import jakarta.annotation.Nullable;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

public class WordKeyboard extends BaseKeyboard {
	protected WordKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<WordAction> buttons,
	                       ButtonMarker backButton, MenuButtonType backButtonIdType, MessageSource messageSource) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType, messageSource);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (WordAction) buttonMarker)
		              .map(button -> new KeyboardEntry(MenuButtonType.WORD, button.id(),
		                                               messageSource.getMessage(button.actionType() == WordActionType.KNOW
		                                                                        ? "button.word.know" : "button.word.dont-know", null,
		                                                                        Locale.ENGLISH),
		                                               button.actionType(), button.lessonId()))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	@Nullable
	protected KeyboardEntry backButton() {
		LessonDto backButton = (LessonDto) this.backButton; // TODO: Если после первого слова нажать на Back button, то должно вернуться WordDto
		return new KeyboardEntry(MenuButtonType.WORD, ActionButtonType.PREVIOUS, backButton.id(), backButton.name(),
		                         backButtonIdType, null, backButton.id());
	}
}
