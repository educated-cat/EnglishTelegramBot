package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.*;
import com.educatedcat.englishtelegrambot.dictionary.word.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import static org.junit.jupiter.api.Assertions.*;

class TranslationServiceTest extends CustomMvcTest {
	@SuppressWarnings("rawtypes")
	private static final PostgreSQLContainer postgresContainer = new PostgreSQLContainer(
			DockerImageName.parse("postgres:15.0"));
	
	@BeforeAll
	public static void beforeAll() {
		postgresContainer.start();
	}
	
	@AfterAll
	public static void afterAll() {
		postgresContainer.stop();
	}
	
	@Autowired
	private TranslationService translationService;
	
	@SneakyThrows
	@ParameterizedTest
	@EnumSource(value = Language.class, names = {"RUS", "DEU"})
	void save(Language language) {
		AbstractTranslation translation = translationService.save(
				new WordDto(null, null, null, null, "translation...", language));
		
		assertNotNull(translation.getId());
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
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
	}
}
