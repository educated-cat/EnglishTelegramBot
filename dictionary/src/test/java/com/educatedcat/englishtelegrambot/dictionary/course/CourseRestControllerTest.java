package com.educatedcat.englishtelegrambot.dictionary.course;

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
class CourseRestControllerTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private CourseService courseService;
	
	@Test
	void findAll() {
		List<Course> res = List.of(new Course() {{
			setId(UUID.randomUUID());
			setName("Course 1");
		}}, new Course() {{
			setId(UUID.randomUUID());
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
