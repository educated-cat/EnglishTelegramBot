package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class WordTextFactoryImpl implements WordTextFactory {
	private final DictionaryClient dictionaryClient;
	private final WordTextHandler wordTextHandler;
	
	@Override
	public String buildText(KeyboardEntry entry) {
		final WordDto word;
		if (entry.idType() == MenuButtonType.LESSON) {
			word = dictionaryClient.findFirstWordInLesson(entry.id());
		} else {
			word = dictionaryClient.findNextWord(entry.id());
		}
		return wordTextHandler.formatWord(word);
	}
}
