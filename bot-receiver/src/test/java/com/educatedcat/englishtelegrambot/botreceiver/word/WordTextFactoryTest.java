package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.DictionaryClient;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.Language;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockBeans({
		@MockBean(DictionaryClient.class)
})
@SpringBootTest(properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class WordTextFactoryTest {
	@Autowired
	private WordTextFactory wordTextFactory;
	
	@Autowired
	private DictionaryClient dictionaryClient;
	
	@Test
	void buildText() {
		doReturn(new WordDto(1L, "Hello", "hɛˈləʊ", "Здравствуй", Language.RUS))
				.when(dictionaryClient)
				.findFirstWordInLesson(any(long.class));
		
		String text = wordTextFactory.buildText(
				new KeyboardEntry(MenuButtonType.LESSON, 1L, "Name", WordActionType.KNOW, 1L));
		
		assertEquals("""
		             Hello - Здравствуй
		             hɛˈləʊ""", text);
	}
}
