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
@Table(name = "deu_translations")
public class DeuTranslation extends AbstractTranslation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deu_translations_id_seq")
	@SequenceGenerator(name = "deu_translations_id_seq", sequenceName = "deu_translations_id_seq")
	@Column(name = "id")
	private Long id;
	
	public DeuTranslation(WordDto dto) {
		super(dto);
	}
}
