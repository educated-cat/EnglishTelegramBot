package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.word.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "rus_translations")
public class RusTranslation extends AbstractTranslation {
	public RusTranslation(WordDto dto) {
		super(dto);
	}
}
