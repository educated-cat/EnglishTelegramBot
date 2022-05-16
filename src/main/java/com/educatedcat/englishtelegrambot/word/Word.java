package com.educatedcat.englishtelegrambot.word;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "words")
public class Word {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",
	                  strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "transcription", nullable = false)
	private String transcription;
	
	public Word(WordDto dto) {
		this.name = dto.name();
		this.transcription = dto.transcription();
	}
	
	WordDto toDto() {
		return new WordDto(id, name, transcription);
	}
	
	void merge(WordDto dto) {
		name = dto.name();
		transcription = dto.transcription();
	}
}