package com.educatedcat.englishtelegrambot.dictionary.word;

import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureEmbeddedDatabase
class WordProductivityRepositoryTest {
	@Autowired
	private WordProductivityRepository wordProductivityRepository;
	
	@Test
	void save() {
		UUID wordId = UUID.randomUUID();
		WordProductivity productivity = new WordProductivity(1L, wordId);
		
		wordProductivityRepository.save(productivity);
		
		WordProductivity created = wordProductivityRepository.findById(1L).orElseThrow();
		assertEquals(1L, created.getUserId());
		assertEquals(wordId, created.getWordId());
		assertEquals(0, created.getProgress());
	}
	
}
