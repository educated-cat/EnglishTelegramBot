package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.word.WordDto;
import org.springframework.stereotype.Component;

/**
 * Builder that creates an entity from language, for example, for {@link Language#RUS} -> {@link RusTranslation}
 */
@Component
public class TranslationBuilder {
	protected AbstractTranslation buildTranslation(WordDto dto) {
		return switch (dto.language()) {
			case RUS -> new RusTranslation(dto);
			case DEU -> new DeuTranslation(dto);
			default -> throw new IllegalArgumentException(dto.language().name());
		};
	}
}
