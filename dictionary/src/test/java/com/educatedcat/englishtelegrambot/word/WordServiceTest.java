package com.educatedcat.englishtelegrambot.word;

import com.educatedcat.englishtelegrambot.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

class WordServiceTest extends CustomMvcTest {
	@Autowired
	private WordService wordService;
	
	@Test
	void updateUnknownWord() {
		wordService.update(UUID.randomUUID(), new WordDto(UUID.randomUUID(), null, null, null, null));
	}
}