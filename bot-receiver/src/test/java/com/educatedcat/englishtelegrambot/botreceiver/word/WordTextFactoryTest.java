package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockBeans({
		@MockBean(DictionaryClient.class)
})
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class WordTextFactoryTest {
	@Autowired
	private WordTextFactory wordTextFactory;
	
	@Autowired
	private DictionaryClient dictionaryClient;
	
	@Test
	void buildText() {
		doReturn(new WordDto(UUID.randomUUID(), "Hello", "hɛˈləʊ", "Здравствуй", Language.RUS))
				.when(dictionaryClient)
				.findFirstWordInLesson(any(UUID.class));
		
		String text = wordTextFactory.buildText(
				new KeyboardEntry(MenuButtonType.LESSON, UUID.randomUUID(), "Name", WordActionType.KNOW));
		
		assertEquals("""
		             Hello - Здравствуй
		             hɛˈləʊ""", text);
	}
}
