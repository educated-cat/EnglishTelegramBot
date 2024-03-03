package com.educatedcat.englishtelegrambot.dictionary.chapter;

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
class ChapterRestControllerTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private ChapterService chapterService;
	
	@Test
	void findAllInCourseByChapterId() {
		List<Chapter> res = List.of(new Chapter() {{
			setId(1);
			setName("Chapter 1");
		}}, new Chapter() {{
			setId(2);
			setName("Chapter 2");
		}});
		List<ChapterDto> convertedRes = res.stream()
		                                   .map(Chapter::toDto)
		                                   .toList();
		long id = 1;
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
			setId(1);
			setName("Chapter 1");
		}}, new Chapter() {{
			setId(2);
			setName("Chapter 2");
		}});
		List<ChapterDto> convertedRes = res.stream()
		                                   .map(Chapter::toDto)
		                                   .toList();
		long id = 1;
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
			setId(1);
			setName("Chapter 1");
		}}, new Chapter() {{
			setId(2);
			setName("Chapter 2");
		}});
		List<ChapterDto> convertedRes = res.stream()
		                                   .map(Chapter::toDto)
		                                   .toList();
		long id = 1;
		given(chapterService.findAllInCourseByLessonId(id)).willReturn(res);
		
		webTestClient.get()
		             .uri("/api/chapters/by-lesson/" + id)
		             .exchange()
		             .expectBody(new ParameterizedTypeReference<List<ChapterDto>>() {
		             })
		             .isEqualTo(convertedRes);
	}
}
