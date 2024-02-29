package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.word.WordDto;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base class for Translation entities. For example, {@link RusTranslation}
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTranslation {
	@Column(nullable = false)
	private String translation;
	
	public AbstractTranslation(WordDto dto) {
		translation = dto.translation();
	}
}
