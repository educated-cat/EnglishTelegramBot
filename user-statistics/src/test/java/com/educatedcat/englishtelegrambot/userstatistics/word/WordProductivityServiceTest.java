package com.educatedcat.englishtelegrambot.userstatistics.word;

import com.educatedcat.englishtelegrambot.userstatistics.dictionary.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import reactor.core.publisher.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(DictionaryClient.class)
})
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class WordProductivityServiceTest {
	@Autowired
	private DictionaryClient dictionaryClient;
	
	@Autowired
	private WordProductivityService wordProductivityService;
	
	@Test
	void getByUserId() {
		long userId = 1011;
		WordProductivityDto productivityDto = new WordProductivityDto(10, 11, 12);
		given(dictionaryClient.getWordProductivity(userId)).willReturn(Mono.just(productivityDto));
		
		var productivityMono = wordProductivityService.getByUserId(userId);
		
		var actual = productivityMono.block();
		assert actual != null;
		assertThat(actual.fullyLearnedWords()).isEqualTo(10);
		assertThat(actual.partlyLearnedWords()).isEqualTo(11);
		assertThat(actual.notLearnedWords()).isEqualTo(12);
	}
}
