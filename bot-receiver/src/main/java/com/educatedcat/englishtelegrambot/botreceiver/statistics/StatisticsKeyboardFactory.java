package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import com.educatedcat.englishtelegrambot.botreceiver.start.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class StatisticsKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		return new StatisticsKeyboard(keyboardEntryMapper, Collections.emptyList(),
		                              new StartButton(messageSource.getMessage("button.back.message", null,
		                                                                       Locale.ENGLISH)),
		                              MenuButtonType.STATISTICS);
	}
}
