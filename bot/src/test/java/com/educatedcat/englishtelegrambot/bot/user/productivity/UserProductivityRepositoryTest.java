package com.educatedcat.englishtelegrambot.bot.user.productivity;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserProductivityRepositoryTest {
	@Autowired
	private UserProductivityRepository userProductivityRepository;
	
	@Test
	void save() {
		UUID wordId = UUID.randomUUID();
		UserProductivity productivity = new UserProductivity(1L, wordId);
		
		userProductivityRepository.save(productivity);
		
		UserProductivity created = userProductivityRepository.findById(1L).orElseThrow();
		assertEquals(1L, created.getUserId());
		assertEquals(wordId, created.getWordId());
		assertEquals(0, created.getProgress());
	}
	
}
