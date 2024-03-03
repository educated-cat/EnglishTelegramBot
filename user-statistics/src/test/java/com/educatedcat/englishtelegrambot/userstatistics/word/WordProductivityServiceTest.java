package com.educatedcat.englishtelegrambot.userstatistics.word;

import com.educatedcat.englishtelegrambot.userstatistics.dictionary.DictionaryClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

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
		given(dictionaryClient.getWordProductivity(userId)).willReturn(productivityDto);
		
		var actual = wordProductivityService.getByUserId(userId);
		
		assertThat(actual.fullyLearnedWords()).isEqualTo(10);
		assertThat(actual.partlyLearnedWords()).isEqualTo(11);
		assertThat(actual.notLearnedWords()).isEqualTo(12);
	}
}
