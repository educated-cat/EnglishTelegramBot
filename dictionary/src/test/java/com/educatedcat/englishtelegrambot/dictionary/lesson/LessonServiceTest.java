package com.educatedcat.englishtelegrambot.dictionary.lesson;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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
		List<Lesson> list = lessonService.findAllInChapterByLessonId(1);
		
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
		List<Lesson> list = lessonService.findAllByChapterId(1);
		
		assertThat(list.size()).isEqualTo(4);
	}
}