package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.translation.*;
import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.jdbc.*;

import java.util.*;

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
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void find() {
		Word res = wordService.find(UUID.fromString("9109e810-fae5-4e9b-baaa-6d5fc1600a47")).get();
		
		assertThat(res).isNotNull();
		assertThat(res.getName()).isEqualTo("Word 1");
		assertThat(res.getTranscription()).isEqualTo("wɜːrd 1");
		assertThat(res.getTranslation()).isInstanceOf(RusTranslation.class);
		assertThat(res.getTranslation().getId()).isEqualTo(UUID.fromString("815fc7ef-ed5b-4835-94da-ff473f54a73b"));
		assertThat(res.getIndex()).isEqualTo(3);
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void findAllByLessonId() {
		List<Word> res = wordService.findAllByLessonId(UUID.fromString("3a7d5900-96fb-423b-9d23-cd2f0c18695e"));
		
		assertThat(res.size()).isEqualTo(2);
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql"
	})
	void save() {
		Word saved = wordService.save(
				new WordDto(null, null, "New word", "transcription...", "Новое слово", Language.RUS));
		
		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getIndex()).isGreaterThan(0);
		assertThat(saved.getName()).isEqualTo("New word");
		assertThat(saved.getTranscription()).isEqualTo("transcription...");
		assertThat(saved.getTranslation()).isNotNull();
		assertThat(saved.getTranslation().getTranslation()).isEqualTo("Новое слово");
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void findFirstInLessonByLessonId() {
		Word res = wordService.findFirstInLessonByLessonId(UUID.fromString("3a7d5900-96fb-423b-9d23-cd2f0c18695e"))
		                      .get();
		
		assertThat(res).isNotNull();
		assertThat(res.getId()).isEqualTo(UUID.fromString("9109e810-fae5-4e9b-baaa-6d5fc1600a47"));
		assertThat(res.getName()).isEqualTo("Word 1");
		assertThat(res.getTranscription()).isEqualTo("wɜːrd 1");
		assertThat(res.getTranslation()).isInstanceOf(RusTranslation.class);
		assertThat(res.getTranslation().getId()).isEqualTo(UUID.fromString("815fc7ef-ed5b-4835-94da-ff473f54a73b"));
		assertThat(res.getIndex()).isEqualTo(3);
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void findNext() {
		Word res = wordService.findNext(UUID.fromString("9109e810-fae5-4e9b-baaa-6d5fc1600a47")).get();
		
		assertThat(res).isNotNull();
		assertThat(res.getId()).isEqualTo(UUID.fromString("d800edd8-551a-4640-b749-fdf967ec99ef"));
		assertThat(res.getName()).isEqualTo("Word 2");
		assertThat(res.getTranscription()).isEqualTo("wɜːrd 2");
		assertThat(res.getTranslation()).isInstanceOf(RusTranslation.class);
		assertThat(res.getTranslation().getId()).isEqualTo(UUID.fromString("c40d802e-fb9d-471d-83ec-c1b7faf6225b"));
		assertThat(res.getIndex()).isEqualTo(4);
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void findNextIsEmpty() {
		assertThatThrownBy(() -> wordService.findNext(UUID.fromString("5e93b22a-62c1-4192-93d5-762acf2fa115")))
				.isInstanceOf(NoMoreWordsException.class);
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
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
		
		wordService.update(UUID.fromString("9109e810-fae5-4e9b-baaa-6d5fc1600a47"), toUpdate);
		
		Word updated = wordService.find(UUID.fromString("9109e810-fae5-4e9b-baaa-6d5fc1600a47")).get();
		
		assertThat(updated).isNotNull();
		assertThat(updated.getName()).isEqualTo("Updated word");
		assertThat(updated.getTranscription()).isEqualTo("Updated transcription");
		assertThat(updated.getTranslation()).isInstanceOf(RusTranslation.class);
		
		RusTranslation translation = rusTranslationTestRepository.findById(updated.getTranslation().getId()).get();
		assertThat(translation.getTranslation()).isEqualTo("Updated translation");
	}
	
	@Test
	@Sql({
			"classpath:db/clean-all.sql",
			"classpath:db/course/courses.sql",
			"classpath:db/chapter/chapters.sql",
			"classpath:db/lesson/lessons.sql",
			"classpath:db/translation/rus/translations.sql",
			"classpath:db/word/words.sql",
			"classpath:db/lessons_words/lessons_words.sql"
	})
	void updateUnknownWord() {
		WordDto toUpdate = new WordDto(UUID.randomUUID(), null, null, null, null, null);
		
		assertThatThrownBy(() -> wordService.update(UUID.randomUUID(), toUpdate))
				.isInstanceOf(NoSuchElementException.class);
	}
}
