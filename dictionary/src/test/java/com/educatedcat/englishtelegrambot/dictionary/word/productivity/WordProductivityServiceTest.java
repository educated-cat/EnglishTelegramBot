package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import com.educatedcat.englishtelegrambot.dictionary.user.productivity.*;
import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.jdbc.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@AutoConfigureEmbeddedDatabase
@SpringBootTest(properties = {
		"spring.main.lazy-initialization=true",
})
class WordProductivityServiceTest {
	@Autowired
	private WordProductivityService wordProductivityService;
	
	@SpyBean
	private WordProductivityRepository wordProductivityRepository;
	
	@Captor
	private ArgumentCaptor<Byte> byteArgumentCaptor;
	
	@BeforeEach
	void beforeEach() {
		reset(wordProductivityRepository);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void increaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.fromString("9109e810-fae5-4e9b-baaa-6d5fc1600a47");
		
		wordProductivityService.increaseWordProductivity(userId, wordId);
		
		WordProductivity updated = wordProductivityRepository.findByUserIdAndWordId(userId, wordId).get();
		assertThat(updated).isNotNull();
		assertThat(updated.getProgress()).isGreaterThan((byte) 0);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/word/word_productivity.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void decreaseUserProductivity() {
		long userId = 123L;
		UUID wordId = UUID.fromString("d800edd8-551a-4640-b749-fdf967ec99ef");
		
		wordProductivityService.decreaseWordProductivity(userId, wordId);
		
		WordProductivity updated = wordProductivityRepository.findByUserIdAndWordId(userId, wordId).get();
		assertThat(updated).isNotNull();
		assertThat(updated.getProgress()).isLessThan((byte) 25);
	}
	
	@Test
	void increaseUserProductivityForUnknownUserIdAndWordId() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		
		wordProductivityService.increaseWordProductivity(userId, wordId);
		
		WordProductivity updated = wordProductivityRepository.findByUserIdAndWordId(userId, wordId).get();
		assertThat(updated).isNotNull();
		assertThat(updated.getProgress()).isGreaterThan((byte) 0);
		then(wordProductivityRepository).should().save(any(WordProductivity.class));
	}
	
	@Test
	void decreaseUserProductivityForUnknownUserIdAndWordId() {
		long userId = 123L;
		UUID wordId = UUID.randomUUID();
		
		wordProductivityService.decreaseWordProductivity(userId, wordId);
		
		WordProductivity updated = wordProductivityRepository.findByUserIdAndWordId(userId, wordId).get();
		assertThat(updated).isNotNull();
		assertThat(updated.getProgress()).isEqualTo((byte) 0);
		then(wordProductivityRepository).should().save(any(WordProductivity.class));
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/word/word_productivity.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void getByUserId() {
		long userId = 123L;
		
		WordProductivityDto productivity = wordProductivityService.getByUserId(userId);
		
		assertThat(productivity).isNotNull();
		assertThat(productivity.fullyLearnedWords()).isEqualTo(1);
		assertThat(productivity.notLearnedWords()).isEqualTo(1);
		assertThat(productivity.partlyLearnedWords()).isEqualTo(1);
		
		then(wordProductivityRepository).should(times(2))
		                                .countByUserIdAndProgress(eq(userId), byteArgumentCaptor.capture());
		then(wordProductivityRepository).should().countByUserIdAndProgressBetween(userId, (byte) 1, (byte) 99);
		
		List<Byte> bytes = byteArgumentCaptor.getAllValues();
		assertThat(bytes.size()).isEqualTo(2);
		assertThat(bytes.stream()
		                .filter(b -> b.equals((byte) 100) || b.equals((byte) 0))
		                .toList()
		                .size()).isEqualTo(2);
	}
}
