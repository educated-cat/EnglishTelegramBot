package com.educatedcat.englishtelegrambot.userstatistics.user;

import com.educatedcat.englishtelegrambot.userstatistics.word.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import reactor.core.publisher.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class UserProductivityFacadeTest {
	@MockBean
	private WordProductivityService wordProductivityService;
	
	@Autowired
	private UserProductivityFacade userProductivityFacade;
	
	@Test
	void prepareUserProductivity() {
		long userId = 1011;
		WordProductivityDto productivityDto = new WordProductivityDto(10, 11, 12);
		given(wordProductivityService.getByUserId(userId)).willReturn(Mono.just(productivityDto));
		
		var userProductivity = userProductivityFacade.prepareUserProductivity(userId);
		
		assertThat(userProductivity.wordProductivity()).isEqualTo(productivityDto);
	}
}
