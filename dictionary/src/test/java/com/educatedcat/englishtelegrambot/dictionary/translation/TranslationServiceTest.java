package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.CustomMvcTest;
import com.educatedcat.englishtelegrambot.dictionary.word.WordDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TranslationServiceTest extends CustomMvcTest {
	@Autowired
	private TranslationService translationService;
	
	@SneakyThrows
	@ParameterizedTest
	@EnumSource(value = Language.class, names = {"RUS", "DEU"})
	void save(Language language) {
		AbstractTranslation translation = translationService.save(
				new WordDto(null, null, null, null, "translation...", language));
		
		if (language == Language.RUS) {
			assertNotNull(((RusTranslation) translation).getId());
		} else {
			assertNotNull(((DeuTranslation) translation).getId());
		}
		assertEquals("translation...", translation.getTranslation());
		checkTranslation(language, translation);
	}
	
	@Test
	@SneakyThrows
	void saveIncorrectLanguage() {
		assertThrows(IllegalArgumentException.class, () -> translationService.save(
				new WordDto(null, null, null, null, "translation...", Language.ENG)));
	}
	
	private void checkTranslation(Language language, AbstractTranslation translation) {
		switch (language) {
			case RUS -> assertTrue(translation instanceof RusTranslation);
			case DEU -> assertTrue(translation instanceof DeuTranslation);
			default -> fail();
		}
	}
}
