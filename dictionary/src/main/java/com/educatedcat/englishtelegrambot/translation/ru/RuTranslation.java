package com.educatedcat.englishtelegrambot.translation.ru;

import com.educatedcat.englishtelegrambot.translation.*;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "ru_translations")
public class RuTranslation extends BaseTranslation {
	public RuTranslation(TranslationDto dto) {
		super(dto);
	}
	
	@Override
	protected TranslationDto toDto() {
		return new TranslationDto(getId(), getTranslation(), Language.RU);
	}
}
