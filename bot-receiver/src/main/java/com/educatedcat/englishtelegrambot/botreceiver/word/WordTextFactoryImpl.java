package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.DictionaryClient;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
