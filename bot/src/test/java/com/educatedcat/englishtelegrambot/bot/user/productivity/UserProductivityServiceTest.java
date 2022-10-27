package com.educatedcat.englishtelegrambot.bot.user.productivity;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.*;

import java.util.*;

import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(DictionaryProperties.class),
		@MockBean(DictionaryConfig.class),
		@MockBean(WebClient.class),
		@MockBean(TelegramBotsApi.class),
		@MockBean(UserProductivityRepository.class)
})
@SpringBootTest(properties = {"spring.main.lazy-initialization=true"})
class UserProductivityServiceTest {
	@Autowired
	private UserProductivityService userProductivityService;
	
	@Autowired
	private UserProductivityRepository userProductivityRepository;
	
	@Test
	void increaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(userProductivityRepository.findByUserIdAndWordId(userId, wordId)).willReturn(
				Optional.of(new UserProductivity(userId, wordId)));
		
		userProductivityService.increaseUserProductivity(userId, wordId);
		
		then(userProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(userProductivityRepository).should(never()).save(any(UserProductivity.class));
	}
	
	@Test
	void decreaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(userProductivityRepository.findByUserIdAndWordId(userId, wordId)).willReturn(
				Optional.of(new UserProductivity(userId, wordId)));
		
		userProductivityService.decreaseUserProductivity(userId, wordId);
		
		then(userProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(userProductivityRepository).should(never()).save(any(UserProductivity.class));
	}
	
	@Test
	void increaseUserProductivityForUnknownUserIdAndWordId() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(userProductivityRepository.save(any(UserProductivity.class))).willReturn(
				new UserProductivity(userId, wordId));
		
		userProductivityService.increaseUserProductivity(userId, wordId);
		
		then(userProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(userProductivityRepository).should().save(any(UserProductivity.class));
	}
	
	@Test
	void decreaseUserProductivityForUnknownUserIdAndWordId() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		given(userProductivityRepository.save(any(UserProductivity.class))).willReturn(
				new UserProductivity(userId, wordId));
		
		userProductivityService.decreaseUserProductivity(userId, wordId);
		
		then(userProductivityRepository).should().findByUserIdAndWordId(userId, wordId);
		then(userProductivityRepository).should().save(any(UserProductivity.class));
	}
}
