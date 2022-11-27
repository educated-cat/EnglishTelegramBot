package com.educatedcat.englishtelegrambot.userstatistics.user;

import com.educatedcat.englishtelegrambot.userstatistics.word.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.reactive.server.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(UserProductivityFacade.class)
})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserProductivityControllerTest {
	
	@Autowired
	private WebTestClient client;
	
	@Autowired
	private UserProductivityFacade userProductivityFacade;
	
	@Test
	void getUserProductivity() {
		long userId = 123;
		UserProductivityDto userProductivity = new UserProductivityDto(new WordProductivityDto(10, 5, 85));
		given(userProductivityFacade.prepareUserProductivity(userId)).willReturn(userProductivity);
		
		UserProductivityDto res = client.get()
		                                .uri("/api/statistics/" + userId)
		                                .accept(MediaType.APPLICATION_JSON)
		                                .exchange()
		                                .expectBody(UserProductivityDto.class)
		                                .returnResult()
		                                .getResponseBody();
		
		assertThat(res).isNotNull();
		assertThat(res.wordProductivity().fullyLearnedWords()).isEqualTo(10);
		assertThat(res.wordProductivity().partlyLearnedWords()).isEqualTo(5);
		assertThat(res.wordProductivity().notLearnedWords()).isEqualTo(85);
	}
	
}
