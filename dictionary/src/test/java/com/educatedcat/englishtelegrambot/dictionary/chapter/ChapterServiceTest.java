package com.educatedcat.englishtelegrambot.dictionary.chapter;

import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.jdbc.*;

import java.util.*;

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
		List<Chapter> list = chapterService.findAllInCourseByChapterId(
				UUID.fromString("7b67037c-76db-46b0-bfe2-7fd3fecb5e98"));
		
		assertThat(list.size()).isEqualTo(3);
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql"
	})
	void findAllByCourseId() {
		List<Chapter> list = chapterService.findAllByCourseId(
				UUID.fromString("d5503c87-b259-4014-8f76-42096c99a0a7"));
		
		assertThat(list.size()).isEqualTo(3);
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql"
	})
	void findAllInCourseByLessonId() {
		List<Chapter> list = chapterService.findAllInCourseByLessonId(
				UUID.fromString("3a7d5900-96fb-423b-9d23-cd2f0c18695e"));
		
		assertThat(list.size()).isEqualTo(3);
	}
}