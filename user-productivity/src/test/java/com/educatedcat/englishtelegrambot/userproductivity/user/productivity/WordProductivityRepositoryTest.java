package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import com.educatedcat.englishtelegrambot.userproductivity.word.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WordProductivityRepositoryTest {
	@Autowired
	private UserProductivityRepository userProductivityRepository;
	
	@Test
	void save() {
		UUID wordId = UUID.randomUUID();
		WordProductivity productivity = new WordProductivity(1L, wordId);
		
		userProductivityRepository.save(productivity);
		
		WordProductivity created = userProductivityRepository.findById(1L).orElseThrow();
		assertEquals(1L, created.getUserId());
		assertEquals(wordId, created.getWordId());
		assertEquals(0, created.getProgress());
	}
	
}
