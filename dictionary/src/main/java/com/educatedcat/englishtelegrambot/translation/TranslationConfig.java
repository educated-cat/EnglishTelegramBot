package com.educatedcat.englishtelegrambot.translation;

import org.springframework.context.annotation.*;

import java.util.*;

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
