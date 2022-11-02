package com.educatedcat.englishtelegrambot.bot.word.end;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import com.educatedcat.englishtelegrambot.bot.start.*;
import lombok.*;

import javax.annotation.*;
import java.util.*;

public class EndWordKeyboard extends BaseKeyboard {
	protected EndWordKeyboard(KeyboardEntryMapper keyboardEntryMapper, StartButton backButton) {
		super(keyboardEntryMapper, Collections.emptyList(), backButton, null);
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
