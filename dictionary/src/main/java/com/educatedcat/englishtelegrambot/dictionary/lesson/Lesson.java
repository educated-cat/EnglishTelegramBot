package com.educatedcat.englishtelegrambot.dictionary.lesson;

import com.educatedcat.englishtelegrambot.dictionary.chapter.*;
import com.educatedcat.englishtelegrambot.dictionary.word.*;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToMany
	@JoinTable(name = "lessons_words",
	           joinColumns = {
			           @JoinColumn(name = "lesson_id")
	           },
	           inverseJoinColumns = {
			           @JoinColumn(name = "word_id")
	           }
	)
	private List<Word> words;
	
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Chapter chapter;
	
	LessonDto toDto() {
		return new LessonDto(id, name);
	}
}
