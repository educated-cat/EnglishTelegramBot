package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.Language;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTextFormatterTest {
	private final WordTextHandler wordTextHandler = new WordTextHandlerImpl();
	
	@Test
	void formatWord() {
		WordDto word = new WordDto(1L, "Hello", "hɛˈləʊ", "Здравствуй", Language.RUS);
		
		String formattedWord = wordTextHandler.formatWord(word);
		
		assertEquals("""
		             Hello - Здравствуй
		             hɛˈləʊ""", formattedWord);
	}
}
