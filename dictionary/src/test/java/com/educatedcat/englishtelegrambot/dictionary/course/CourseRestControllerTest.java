package com.educatedcat.englishtelegrambot.dictionary.course;

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
class CourseRestControllerTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private CourseService courseService;
	
	@Test
	void findAll() {
		List<Course> res = List.of(new Course() {{
			setId(1);
			setName("Course 1");
		}}, new Course() {{
			setId(2);
			setName("Course 2");
		}});
		List<CourseDto> convertedRes = res.stream()
		                                  .map(Course::toDto)
		                                  .toList();
		given(courseService.findAll()).willReturn(res);
		
		webTestClient.get()
		             .uri("/api/courses")
		             .exchange()
		             .expectBody(new ParameterizedTypeReference<List<CourseDto>>() {
		             })
		             .isEqualTo(convertedRes);
	}
	
}
