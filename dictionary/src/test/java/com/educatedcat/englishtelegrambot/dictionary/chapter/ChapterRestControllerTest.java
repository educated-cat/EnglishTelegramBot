package com.educatedcat.englishtelegrambot.dictionary.chapter;

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
class ChapterRestControllerTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private ChapterService chapterService;
	
	@Test
	void findAllInCourseByChapterId() {
		List<Chapter> res = List.of(new Chapter() {{
			setId(UUID.randomUUID());
			setName("Chapter 1");
		}}, new Chapter() {{
			setId(UUID.randomUUID());
			setName("Chapter 2");
		}});
		List<ChapterDto> convertedRes = res.stream()
		                                   .map(Chapter::toDto)
		                                   .toList();
		UUID id = UUID.randomUUID();
		given(chapterService.findAllInCourseByChapterId(id)).willReturn(res);
		
		webTestClient.get()
		             .uri("/api/chapters/" + id)
		             .exchange()
		             .expectBody(new ParameterizedTypeReference<List<ChapterDto>>() {
		             })
		             .isEqualTo(convertedRes);
	}
	
	@Test
	void findAllInCourse() {
		List<Chapter> res = List.of(new Chapter() {{
			setId(UUID.randomUUID());
			setName("Chapter 1");
		}}, new Chapter() {{
			setId(UUID.randomUUID());
			setName("Chapter 2");
		}});
		List<ChapterDto> convertedRes = res.stream()
		                                   .map(Chapter::toDto)
		                                   .toList();
		UUID id = UUID.randomUUID();
		given(chapterService.findAllByCourseId(id)).willReturn(res);
		
		webTestClient.get()
		             .uri("/api/chapters/by-course/" + id)
		             .exchange()
		             .expectBody(new ParameterizedTypeReference<List<ChapterDto>>() {
		             })
		             .isEqualTo(convertedRes);
	}
	
	@Test
	void findAllChaptersByLessonId() {
		List<Chapter> res = List.of(new Chapter() {{
			setId(UUID.randomUUID());
			setName("Chapter 1");
		}}, new Chapter() {{
			setId(UUID.randomUUID());
			setName("Chapter 2");
		}});
		List<ChapterDto> convertedRes = res.stream()
		                                   .map(Chapter::toDto)
		                                   .toList();
		UUID id = UUID.randomUUID();
		given(chapterService.findAllInCourseByLessonId(id)).willReturn(res);
		
		webTestClient.get()
		             .uri("/api/chapters/by-lesson/" + id)
		             .exchange()
		             .expectBody(new ParameterizedTypeReference<List<ChapterDto>>() {
		             })
		             .isEqualTo(convertedRes);
	}
}
