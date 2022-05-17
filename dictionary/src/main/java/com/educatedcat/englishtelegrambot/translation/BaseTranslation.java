package com.educatedcat.englishtelegrambot.translation;

import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * Base class for Translation entities. For example, {@link com.educatedcat.englishtelegrambot.translation.ru.RuTranslation}
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseTranslation {
	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "translation", nullable = false)
	private String translation;
	
	public BaseTranslation(TranslationDto dto) {
		this.id = dto.id();
		this.translation = dto.translation();
	}
	
	protected abstract TranslationDto toDto();
	
	void merge(TranslationDto dto) {
		translation = dto.translation();
	}
}
