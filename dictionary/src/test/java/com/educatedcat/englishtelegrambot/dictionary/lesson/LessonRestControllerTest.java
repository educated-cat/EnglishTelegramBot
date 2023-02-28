package com.educatedcat.englishtelegrambot.dictionary.lesson;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.core.*;
import org.springframework.test.web.reactive.server.*;

import java.util.*;

import static org.mockito.BDDMockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class LessonRestControllerTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private LessonService lessonService;
	
	@Test
	void findAllInChapterByLessonId() {
		List<Lesson> res = List.of(new Lesson() {{
			setId(UUID.randomUUID());
			setName("Course 1");
		}}, new Lesson() {{
			setId(UUID.randomUUID());
			setName("Course 2");
		}});
		UUID id = UUID.randomUUID();
		List<LessonDto> convertedRes = res.stream()
		                                  .map(Lesson::toDto)
		                                  .toList();
		given(lessonService.findAllInChapterByLessonId(id)).willReturn(res);
		
		webTestClient.get()
		             .uri("/api/lessons/" + id)
		             .exchange()
		             .expectBody(new ParameterizedTypeReference<List<LessonDto>>() {
		             })
		             .isEqualTo(convertedRes);
	}
	
	@Test
	void findAllInChapter() {
		List<Lesson> res = List.of(new Lesson() {{
			setId(UUID.randomUUID());
			setName("Course 1");
		}}, new Lesson() {{
			setId(UUID.randomUUID());
			setName("Course 2");
		}});
		UUID id = UUID.randomUUID();
		List<LessonDto> convertedRes = res.stream()
		                                  .map(Lesson::toDto)
		                                  .toList();
		given(lessonService.findAllByChapterId(id)).willReturn(res);
		
		webTestClient.get()
		             .uri("/api/lessons/by-chapter/" + id)
		             .exchange()
		             .expectBody(new ParameterizedTypeReference<List<LessonDto>>() {
		             })
		             .isEqualTo(convertedRes);
	}
}
