package com.educatedcat.englishtelegrambot.dictionary.course;

import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.jdbc.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureEmbeddedDatabase
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class CourseServiceTest {
	@Autowired
	private CourseService courseService;
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql"
	})
	void findAll() {
		List<Course> list = courseService.findAll();
		
		assertThat(list.size()).isEqualTo(2);
	}
}