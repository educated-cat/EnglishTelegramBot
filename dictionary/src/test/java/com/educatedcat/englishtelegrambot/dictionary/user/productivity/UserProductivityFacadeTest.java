package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.UpdateWordProductivityDto;
import com.educatedcat.englishtelegrambot.dictionary.word.WordActionType;
import com.educatedcat.englishtelegrambot.dictionary.word.WordProductivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class UserProductivityFacadeTest {
	@Autowired
	private UserProductivityFacade userProductivityFacade;
	
	@MockBean
	private WordProductivityService wordProductivityService;
	
	@Test
	void increaseUserProductivity() {
		long userId = 123L;
		long wordId = 100L;
		
		userProductivityFacade.updateUserProductivity(
				new UpdateWordProductivityDto(userId, wordId,
				                              WordActionType.KNOW));
		
		verify(wordProductivityService).increaseWordProductivity(userId, wordId);
	}
	
	@Test
	void increaseUserProductivitySeveralTimes() {
		long userId = 123L;
		long wordId = 100L;
		
		userProductivityFacade.updateUserProductivity(
				new UpdateWordProductivityDto(userId, wordId,
				                              WordActionType.DONT_KNOW));
		
		verify(wordProductivityService).decreaseWordProductivity(userId, wordId);
	}
}
