package com.educatedcat.englishtelegrambot.translation;

import com.educatedcat.englishtelegrambot.translation.ru.*;

/**
 * Builder that creates an entity from language, for example, for {@link Language}.RU -> {@link RuTranslation}
 */
public class TranslationBuilder {
	protected BaseTranslation buildTranslation(TranslationDto dto) {
		return switch (dto.language()) {
			case EN, RU -> new RuTranslation(dto);
		};
	}
}
