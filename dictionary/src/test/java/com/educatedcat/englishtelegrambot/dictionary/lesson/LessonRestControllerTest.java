package com.educatedcat.englishtelegrambot.dictionary.lesson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

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
			setId(1);
			setName("Course 1");
		}}, new Lesson() {{
			setId(2);
			setName("Course 2");
		}});
		long id = 1;
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
			setId(1);
			setName("Course 1");
		}}, new Lesson() {{
			setId(2);
			setName("Course 2");
		}});
		long id = 1;
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
