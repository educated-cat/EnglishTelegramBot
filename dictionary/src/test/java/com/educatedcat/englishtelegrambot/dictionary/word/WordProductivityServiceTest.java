package com.educatedcat.englishtelegrambot.dictionary.word;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;

import java.util.*;

import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(WordProductivityRepository.class)
})
@SpringBootTest(properties = {"spring.main.lazy-initialization=true"})
class WordProductivityServiceTest {
	@Autowired
	private WordProductivityService wordProductivityService;
	
	@Autowired
	private WordProductivityRepository wordProductivityRepository;
	
	@Test
	void increaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(wordProductivityRepository.findByUserIdAndWordId(userId, wordId)).willReturn(
				Optional.of(new WordProductivity(userId, wordId)));
		
		wordProductivityService.increaseWordProductivity(userId, wordId);
		
		then(wordProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(wordProductivityRepository).should(never()).save(any(WordProductivity.class));
	}
	
	@Test
	void decreaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(wordProductivityRepository.findByUserIdAndWordId(userId, wordId)).willReturn(
				Optional.of(new WordProductivity(userId, wordId)));
		
		wordProductivityService.decreaseWordProductivity(userId, wordId);
		
		then(wordProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(wordProductivityRepository).should(never()).save(any(WordProductivity.class));
	}
	
	@Test
	void increaseUserProductivityForUnknownUserIdAndWordId() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(wordProductivityRepository.save(any(WordProductivity.class))).willReturn(
				new WordProductivity(userId, wordId));
		
		wordProductivityService.increaseWordProductivity(userId, wordId);
		
		then(wordProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(wordProductivityRepository).should().save(any(WordProductivity.class));
	}
	
	@Test
	void decreaseUserProductivityForUnknownUserIdAndWordId() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(wordProductivityRepository.save(any(WordProductivity.class))).willReturn(
				new WordProductivity(userId, wordId));
		
		wordProductivityService.decreaseWordProductivity(userId, wordId);
		
		then(wordProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(wordProductivityRepository).should().save(any(WordProductivity.class));
	}
}
