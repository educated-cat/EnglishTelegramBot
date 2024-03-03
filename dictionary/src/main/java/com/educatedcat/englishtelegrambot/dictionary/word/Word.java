package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.lesson.Lesson;
import com.educatedcat.englishtelegrambot.dictionary.translation.AbstractTranslation;
import com.educatedcat.englishtelegrambot.dictionary.translation.DeuTranslation;
import com.educatedcat.englishtelegrambot.dictionary.translation.Language;
import com.educatedcat.englishtelegrambot.dictionary.translation.RusTranslation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyDiscriminatorValues;
import org.hibernate.annotations.AnyKeyJavaType;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.type.descriptor.java.LongJavaType;

import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@Table(name = "words")
public class Word {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "words_id_seq")
	@SequenceGenerator(name = "words_id_seq", sequenceName = "words_id_seq")
	@Column(name = "id")
	private long id;
	
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
	@AnyKeyJavaType(value = LongJavaType.class)
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
