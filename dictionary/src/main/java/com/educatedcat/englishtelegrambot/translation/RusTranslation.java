package com.educatedcat.englishtelegrambot.translation;

import com.educatedcat.englishtelegrambot.word.*;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "rus_translations")
public class RusTranslation extends AbstractTranslation {
	public RusTranslation(WordDto dto) {
		super(dto);
	}
}
