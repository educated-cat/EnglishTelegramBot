package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import com.educatedcat.englishtelegrambot.userproductivity.word.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;

import java.util.*;

import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(UserProductivityRepository.class)
})
@SpringBootTest(properties = {"spring.main.lazy-initialization=true"})
class WordProductivityServiceTest {
	@Autowired
	private WordProductivityService wordProductivityService;
	
	@Autowired
	private UserProductivityRepository userProductivityRepository;
	
	@Test
	void increaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(userProductivityRepository.findByUserIdAndWordId(userId, wordId)).willReturn(
				Optional.of(new WordProductivity(userId, wordId)));
		
		wordProductivityService.increaseWordProductivity(userId, wordId);
		
		then(userProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(userProductivityRepository).should(never()).save(any(WordProductivity.class));
	}
	
	@Test
	void decreaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(userProductivityRepository.findByUserIdAndWordId(userId, wordId)).willReturn(
				Optional.of(new WordProductivity(userId, wordId)));
		
		wordProductivityService.decreaseWordProductivity(userId, wordId);
		
		then(userProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(userProductivityRepository).should(never()).save(any(WordProductivity.class));
	}
	
	@Test
	void increaseUserProductivityForUnknownUserIdAndWordId() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(userProductivityRepository.save(any(WordProductivity.class))).willReturn(
				new WordProductivity(userId, wordId));
		
		wordProductivityService.increaseWordProductivity(userId, wordId);
		
		then(userProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(userProductivityRepository).should().save(any(WordProductivity.class));
	}
	
	@Test
	void decreaseUserProductivityForUnknownUserIdAndWordId() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(userProductivityRepository.save(any(WordProductivity.class))).willReturn(
				new WordProductivity(userId, wordId));
		
		wordProductivityService.decreaseWordProductivity(userId, wordId);
		
		then(userProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(userProductivityRepository).should().save(any(WordProductivity.class));
	}
}
