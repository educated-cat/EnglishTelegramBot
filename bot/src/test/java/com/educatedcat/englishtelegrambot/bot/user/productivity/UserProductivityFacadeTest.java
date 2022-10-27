package com.educatedcat.englishtelegrambot.bot.user.productivity;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.word.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.*;

import java.util.*;

import static org.mockito.Mockito.*;

@MockBeans({
		@MockBean(DictionaryProperties.class),
		@MockBean(DictionaryConfig.class),
		@MockBean(WebClient.class),
		@MockBean(TelegramBotsApi.class),
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
		
		userProductivityFacade.updateUserProductivity(userId, wordId, WordActionType.KNOW);
		
		verify(userProductivityService).increaseUserProductivity(userId, wordId);
	}
	
	@Test
	void increaseUserProductivitySeveralTimes() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		
		userProductivityFacade.updateUserProductivity(userId, wordId, WordActionType.DONT_KNOW);
		
		verify(userProductivityService).decreaseUserProductivity(userId, wordId);
	}
}
