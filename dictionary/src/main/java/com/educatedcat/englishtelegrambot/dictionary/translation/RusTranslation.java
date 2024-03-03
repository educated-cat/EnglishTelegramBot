package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.word.WordDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "rus_translations")
public class RusTranslation extends AbstractTranslation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rus_translations_id_seq")
	@SequenceGenerator(name = "rus_translations_id_seq", sequenceName = "rus_translations_id_seq")
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	public RusTranslation(WordDto dto) {
		super(dto);
	}
}
