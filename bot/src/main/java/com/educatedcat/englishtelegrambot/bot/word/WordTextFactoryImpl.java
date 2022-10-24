package com.educatedcat.englishtelegrambot.bot.word;

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
		WordDto word = dictionaryClient.findWordsInLesson(entry.id())
		                               .get(0);
		return wordTextHandler.formatWord(word);
	}
}
