package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;

import java.util.*;

import static org.mockito.Mockito.*;

@MockBeans({
		@MockBean(WordProductivityService.class)
})
@SpringBootTest(properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class UserProductivityFacadeTest {
	@Autowired
	private UserProductivityFacade userProductivityFacade;
	
	@Autowired
	private WordProductivityService wordProductivityService;
	
	@Test
	void increaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		
		userProductivityFacade.updateUserProductivity(
				new UpdateWordProductivityDto(userId, wordId,
				                              WordActionType.KNOW));
		
		verify(wordProductivityService).increaseWordProductivity(userId, wordId);
	}
	
	@Test
	void increaseUserProductivitySeveralTimes() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		
		userProductivityFacade.updateUserProductivity(
				new UpdateWordProductivityDto(userId, wordId,
				                              WordActionType.DONT_KNOW));
		
		verify(wordProductivityService).decreaseWordProductivity(userId, wordId);
	}
}
