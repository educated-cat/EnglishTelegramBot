package com.educatedcat.englishtelegrambot.dictionary.lesson;

import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.jdbc.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureEmbeddedDatabase
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class LessonServiceTest {
	@Autowired
	private LessonService lessonService;
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql"
	})
	void findAllInChapterByLessonId() {
		List<Lesson> list = lessonService.findAllInChapterByLessonId(
				UUID.fromString("3a7d5900-96fb-423b-9d23-cd2f0c18695e"));
		
		assertThat(list.size()).isEqualTo(4);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql"
	})
	void findAllByChapterId() {
		List<Lesson> list = lessonService.findAllByChapterId(
				UUID.fromString("7b67037c-76db-46b0-bfe2-7fd3fecb5e98"));
		
		assertThat(list.size()).isEqualTo(4);
	}
}