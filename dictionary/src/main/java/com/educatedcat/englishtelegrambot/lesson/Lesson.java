package com.educatedcat.englishtelegrambot.lesson;

import com.educatedcat.englishtelegrambot.word.*;
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
	
	LessonDto toDto() {
		return new LessonDto(id, name);
	}
}