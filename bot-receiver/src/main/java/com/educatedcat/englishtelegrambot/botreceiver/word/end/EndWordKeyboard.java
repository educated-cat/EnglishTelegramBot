package com.educatedcat.englishtelegrambot.botreceiver.word.end;

import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import com.educatedcat.englishtelegrambot.botreceiver.start.StartButton;
import jakarta.annotation.Nullable;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;

import java.util.Collections;
import java.util.List;

public class EndWordKeyboard extends BaseKeyboard {
	protected EndWordKeyboard(KeyboardEntryMapper keyboardEntryMapper, StartButton backButton, MessageSource messageSource) {
		super(keyboardEntryMapper, Collections.emptyList(), backButton, null, messageSource);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return Collections.emptyList();
	}
	
	@Override
	@SneakyThrows
	@Nullable
	protected KeyboardEntry backButton() {
		StartButton backButton = (StartButton) this.backButton;
		return new KeyboardEntry(MenuButtonType.START, ActionButtonType.NEXT, null, backButton.name(),
		                         MenuButtonType.START);
	}
}
