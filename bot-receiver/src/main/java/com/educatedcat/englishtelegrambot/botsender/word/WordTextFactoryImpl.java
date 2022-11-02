package com.educatedcat.englishtelegrambot.botsender.word;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.dictionary.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
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
