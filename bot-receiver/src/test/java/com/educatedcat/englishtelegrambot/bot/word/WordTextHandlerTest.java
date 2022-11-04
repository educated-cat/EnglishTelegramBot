package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;
import com.educatedcat.englishtelegrambot.botreceiver.word.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WordTextHandlerTest {
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
