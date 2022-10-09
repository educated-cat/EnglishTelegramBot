package com.educatedcat.englishtelegrambot.translation;

import com.educatedcat.englishtelegrambot.word.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
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
	@Type(type = "uuid-char")
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private UUID id;
	
	@Column(nullable = false)
	private String translation;
	
	public AbstractTranslation(WordDto dto) {
		id = UUID.randomUUID();
		translation = dto.translation();
	}
}
