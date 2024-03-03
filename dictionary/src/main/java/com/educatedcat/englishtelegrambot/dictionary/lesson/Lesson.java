package com.educatedcat.englishtelegrambot.dictionary.lesson;

import com.educatedcat.englishtelegrambot.dictionary.chapter.Chapter;
import com.educatedcat.englishtelegrambot.dictionary.word.Word;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessons_id_seq")
	@SequenceGenerator(name = "lessons_id_seq", sequenceName = "lessons_id_seq")
	@Column(name = "id")
	private long id;
	
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
