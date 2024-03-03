package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.translation.Language;
import com.educatedcat.englishtelegrambot.dictionary.translation.RusTranslation;
import com.educatedcat.englishtelegrambot.dictionary.user.productivity.WordProductivityDto;
import com.educatedcat.englishtelegrambot.dictionary.word.productivity.WordProductivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class WordRestControllerTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private WordService wordService;
	
	@MockBean
	private WordProductivityService wordProductivityService;
	
	@Test
	void findById() {
		long id = 1;
		WordDto dto = new WordDto(0L, null, "cat", "caat", "", Language.RUS);
		Word word = new Word(dto, new RusTranslation(dto));
		given(wordService.find(id)).willReturn(Optional.of(word));
		
		webTestClient.get()
		             .uri("/api/words/{wordId}", id)
		             .exchange()
		             .expectBody(WordDto.class)
		             .isEqualTo(dto);
	}
	
	@Test
	void findFirstInLessonByLessonId() {
		long lessonId = 1;
		WordDto dto = new WordDto(0L, null, "cat", "caat", "", Language.RUS);
		Word word = new Word(dto, new RusTranslation(dto));
		given(wordService.findFirstInLessonByLessonId(lessonId)).willReturn(Optional.of(word));
		
		webTestClient.get()
		             .uri("/api/words/first/{lessonId}", lessonId)
		             .exchange()
		             .expectBody(WordDto.class)
		             .isEqualTo(dto);
	}
	
	@Test
	void findNext() {
		long previousWordId = 1;
		WordDto dto = new WordDto(0L, null, "cat", "caat", "", Language.RUS);
		Word word = new Word(dto, new RusTranslation(dto));
		given(wordService.findNext(previousWordId)).willReturn(Optional.of(word));
		
		webTestClient.get()
		             .uri("/api/words/next/{previousWordId}", previousWordId)
		             .exchange()
		             .expectBody(WordDto.class)
		             .isEqualTo(dto);
	}
	
	@Test
	void findAllByLessonId() {
		long lessonId = 1;
		WordDto dto = new WordDto(0L, null, "cat", "caat", "", Language.RUS);
		Word word = new Word(dto, new RusTranslation(dto));
		given(wordService.findAllByLessonId(lessonId)).willReturn(List.of(word, word, word));
		
		webTestClient.get()
		             .uri("/api/words/by-lesson/{lessonId}", lessonId)
		             .exchange()
		             .expectBody(new ParameterizedTypeReference<List<WordDto>>() {
		             })
		             .isEqualTo(List.of(dto, dto, dto));
	}
	
	@Test
	void findWordProductivityByUserId() {
		long userId = 12345L;
		WordProductivityDto dto = new WordProductivityDto(1, 1, 1);
		given(wordProductivityService.getByUserId(userId)).willReturn(dto);
		
		webTestClient.get()
		             .uri("/api/words/productivity/{userId}", userId)
		             .exchange()
		             .expectBody(WordProductivityDto.class)
		             .isEqualTo(dto);
	}
	
	@Test
	void create() {
		WordDto dto = new WordDto(0L, null, "cat", "caat", "", Language.RUS);
		given(wordService.save(dto)).willReturn(new Word(dto, new RusTranslation(dto)));
		
		webTestClient.post()
		             .uri("/api/words")
		             .bodyValue(dto)
		             .exchange()
		             .expectBody(WordDto.class)
		             .isEqualTo(dto);
	}
	
	@Test
	void update() {
		long id = 1;
		WordDto dto = new WordDto(0L, null, "cat", "caat", "", Language.RUS);
		
		webTestClient.put()
		             .uri("/api/words/{id}", id)
		             .bodyValue(dto)
		             .exchange()
		             .expectStatus()
		             .isEqualTo(HttpStatus.OK);
	}
}
