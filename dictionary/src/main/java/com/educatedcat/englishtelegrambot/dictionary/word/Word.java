package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.lesson.*;
import com.educatedcat.englishtelegrambot.dictionary.translation.*;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.*;
import org.hibernate.type.descriptor.java.*;

import java.util.*;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@Table(name = "words")
public class Word {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "transcription", nullable = false)
	private String transcription;
	
	@Generated(GenerationTime.INSERT)
	@Column(nullable = false, unique = true, updatable = false)
	private Long index;
	
	@SuppressWarnings("JpaAttributeTypeInspection")
	@Any(fetch = FetchType.LAZY)
	@AnyDiscriminator
	@AnyDiscriminatorValues({
			@AnyDiscriminatorValue(discriminator = "RUS", entity = RusTranslation.class),
			@AnyDiscriminatorValue(discriminator = "DEU", entity = DeuTranslation.class)
	})
	@AnyKeyJavaType(value = UUIDJavaType.class)
	@Column(name = "translation_type", length = 3)
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
