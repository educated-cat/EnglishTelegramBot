package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.word.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

/**
 * Base class for Translation entities. For example, {@link RusTranslation}
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTranslation {
	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(nullable = false)
	private String translation;
	
	public AbstractTranslation(WordDto dto) {
		id = UUID.randomUUID();
		translation = dto.translation();
	}
}
