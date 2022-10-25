package com.educatedcat.englishtelegrambot.word;

import com.educatedcat.englishtelegrambot.lesson.*;
import com.educatedcat.englishtelegrambot.translation.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@Table(name = "words")
public class Word {
	@Id
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "transcription", nullable = false)
	private String transcription;
	
	@Generated(GenerationTime.INSERT)
	@Column(nullable = false, unique = true, updatable = false)
	private Long index;
	
	@SuppressWarnings({"deprecation", "JpaAttributeTypeInspection"})
	@Any(metaDef = "WordMetaDef",
	     metaColumn = @Column(name = "translation_type", nullable = false, length = 3),
	     fetch = FetchType.LAZY)
	@AnyMetaDef(name = "WordMetaDef",
	            metaType = "string",
	            idType = "uuid-char",
	            metaValues = {
			            @MetaValue(value = "RUS", targetEntity = RusTranslation.class),
			            @MetaValue(value = "DEU", targetEntity = DeuTranslation.class)
	            })
	@JoinColumn(name = "translation_id")
	private AbstractTranslation translation;
	
	@ManyToMany
	@JoinTable(name = "lessons_words",
	           joinColumns = {
			           @JoinColumn(name = "word_id")
	           },
	           inverseJoinColumns = {
			           @JoinColumn(name = "lesson_id")
	           }
	)
	private List<Lesson> lessons;
	
	public Word(WordDto dto, AbstractTranslation translation) {
		this.name = dto.name();
		this.transcription = dto.transcription();
		this.translation = translation;
	}
	
	WordDto toDto() {
		return new WordDto(id, index, name, transcription, translation.getTranslation(), Language.RUS);
	}
	
	void merge(WordDto dto) {
		name = dto.name();
		transcription = dto.transcription();
		translation.setTranslation(dto.translation());
	}
}
