package com.educatedcat.englishtelegrambot.dictionary.chapter;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureEmbeddedDatabase
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class ChapterServiceTest {
	@Autowired
	private ChapterService chapterService;
	
	@Test
	@Sql({
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql"
	})
	void findChaptersById() {
		List<Chapter> list = chapterService.findAllInCourseByChapterId(1);
		
		assertThat(list.size()).isEqualTo(3);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql"
	})
	void findAllByCourseId() {
		List<Chapter> list = chapterService.findAllByCourseId(1);
		
		assertThat(list.size()).isEqualTo(3);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql"
	})
	void findAllInCourseByLessonId() {
		List<Chapter> list = chapterService.findAllInCourseByLessonId(1);
		
		assertThat(list.size()).isEqualTo(3);
	}
}