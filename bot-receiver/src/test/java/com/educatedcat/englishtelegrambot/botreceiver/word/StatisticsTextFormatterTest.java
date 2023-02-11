package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTextFormatterTest {
	private final WordTextHandler wordTextHandler = new WordTextHandlerImpl();
	
	@Test
	void formatWord() {
		WordDto word = new WordDto(UUID.randomUUID(), "Hello", "hɛˈləʊ", "Здравствуй", Language.RUS);
		
		String formattedWord = wordTextHandler.formatWord(word);
		
		assertEquals("""
		             Hello - Здравствуй
		             hɛˈləʊ""", formattedWord);
	}
}