package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class WordKeyboardFactory extends AbstractCallbackKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build() {
		final List<WordDto> buttons = List.of(new WordDto(null, "White - белый"),
		                                      new WordDto(null, "Black - чёрный"),
		                                      new WordDto(null, "Brown - коричневый"),
		                                      new WordDto(null, "Yellow - жёлтый"));
		return new WordKeyboard(keyboardEntryMapper, messageSource, buttons);
	}
}
