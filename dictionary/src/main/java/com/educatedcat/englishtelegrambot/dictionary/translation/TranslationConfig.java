package com.educatedcat.englishtelegrambot.dictionary.translation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;

@Configuration
public class TranslationConfig {
	@Bean
	public EnumMap<Language, TranslationRepository> translationRepositories(
			RusTranslationRepository rusTranslationRepository, DeuTranslationRepository deuTranslationRepository) {
		return new EnumMap<>(Language.class) {{
			put(Language.RUS, rusTranslationRepository);
			put(Language.DEU, deuTranslationRepository);
		}};
	}
}
