package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.translation.Language;
import com.educatedcat.englishtelegrambot.dictionary.translation.RusTranslation;
import com.educatedcat.englishtelegrambot.dictionary.translation.RusTranslationTestRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@AutoConfigureEmbeddedDatabase
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class WordServiceTest {
	@Autowired
	private WordService wordService;
	
	@Autowired
	private RusTranslationTestRepository rusTranslationTestRepository;
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void find() {
		Word res = wordService.find(1).get();
		
		assertThat(res).isNotNull();
		assertThat(res.getName()).isEqualTo("Word 1");
		assertThat(res.getTranscription()).isEqualTo("wɜːrd 1");
		assertThat(res.getTranslation()).isInstanceOf(RusTranslation.class);
		assertThat(((RusTranslation) res.getTranslation()).getId()).isEqualTo(1);
		assertThat(res.getIndex()).isEqualTo(3);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void findAllByLessonId() {
		List<Word> res = wordService.findAllByLessonId(1);
		
		assertThat(res.size()).isEqualTo(2);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql"
	})
	void save() {
		Word saved = wordService.save(
				new WordDto(null, null, "New word", "transcription...", "Новое слово", Language.RUS));
		
		assertThat(saved).isNotNull();
		assertThat(saved.getIndex()).isGreaterThan(0);
		assertThat(saved.getName()).isEqualTo("New word");
		assertThat(saved.getTranscription()).isEqualTo("transcription...");
		assertThat(saved.getTranslation()).isNotNull();
		assertThat(saved.getTranslation().getTranslation()).isEqualTo("Новое слово");
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void findFirstInLessonByLessonId() {
		Word res = wordService.findFirstInLessonByLessonId(1)
		                      .get();
		
		assertThat(res).isNotNull();
		assertThat(res.getId()).isEqualTo(1);
		assertThat(res.getName()).isEqualTo("Word 1");
		assertThat(res.getTranscription()).isEqualTo("wɜːrd 1");
		assertThat(res.getTranslation()).isInstanceOf(RusTranslation.class);
		assertThat(((RusTranslation) res.getTranslation()).getId()).isEqualTo(1);
		assertThat(res.getIndex()).isEqualTo(3);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void findNext() {
		Word res = wordService.findNext(1).get();
		
		assertThat(res).isNotNull();
		assertThat(res.getId()).isEqualTo(2);
		assertThat(res.getName()).isEqualTo("Word 2");
		assertThat(res.getTranscription()).isEqualTo("wɜːrd 2");
		assertThat(res.getTranslation()).isInstanceOf(RusTranslation.class);
		assertThat(((RusTranslation) res.getTranslation()).getId()).isEqualTo(2);
		assertThat(res.getIndex()).isEqualTo(4);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void findNextIsEmpty() {
		assertThatThrownBy(() -> wordService.findNext(5))
				.isInstanceOf(NoMoreWordsException.class);
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void update() {
		WordDto toUpdate = new WordDto(null, null, "Updated word", "Updated transcription", "Updated translation",
		                               null);
		
		wordService.update(1, toUpdate);
		
		Word updated = wordService.find(1).get();
		
		assertThat(updated).isNotNull();
		assertThat(updated.getName()).isEqualTo("Updated word");
		assertThat(updated.getTranscription()).isEqualTo("Updated transcription");
		assertThat(updated.getTranslation()).isInstanceOf(RusTranslation.class);
		
		RusTranslation translation = rusTranslationTestRepository
				.findById(((RusTranslation) updated.getTranslation()).getId()).get();
		assertThat(translation.getTranslation()).isEqualTo("Updated translation");
	}
	
	@Test
	@Sql({
			"classpath:db/clear-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void updateUnknownWord() {
		WordDto toUpdate = new WordDto(null, null, null, null, null, null);
		
		assertThatThrownBy(() -> wordService.update(-9999, toUpdate))
				.isInstanceOf(NoSuchElementException.class);
	}
}
