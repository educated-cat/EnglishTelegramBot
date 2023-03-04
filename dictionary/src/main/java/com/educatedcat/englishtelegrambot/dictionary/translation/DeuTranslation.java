package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.word.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "deu_translations")
public class DeuTranslation extends AbstractTranslation {
	public DeuTranslation(WordDto dto) {
		super(dto);
	}
}
