package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import com.educatedcat.englishtelegrambot.userproductivity.word.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;

import java.util.*;

import static org.mockito.Mockito.*;

@MockBeans({
		@MockBean(UserProductivityService.class)
})
@SpringBootTest(properties = {"spring.main.lazy-initialization=true"})
class UserProductivityFacadeTest {
	@Autowired
	private UserProductivityFacade userProductivityFacade;
	
	@Autowired
	private UserProductivityService userProductivityService;
	
	@Test
	void increaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		
		userProductivityFacade.updateUserProductivity(new UserProductivityDto(userId, wordId, WordActionType.KNOW));
		
		verify(userProductivityService).increaseUserProductivity(userId, wordId);
	}
	
	@Test
	void increaseUserProductivitySeveralTimes() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		
		userProductivityFacade.updateUserProductivity(
				new UserProductivityDto(userId, wordId, WordActionType.DONT_KNOW));
		
		verify(userProductivityService).decreaseUserProductivity(userId, wordId);
	}
}
