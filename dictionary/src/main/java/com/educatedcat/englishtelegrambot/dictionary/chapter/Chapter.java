package com.educatedcat.englishtelegrambot.dictionary.chapter;

import com.educatedcat.englishtelegrambot.dictionary.course.Course;
import com.educatedcat.englishtelegrambot.dictionary.lesson.Lesson;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "chapters")
public class Chapter {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chapters_id_seq")
	@SequenceGenerator(name = "chapters_id_seq", sequenceName = "chapters_id_seq")
	@Column(name = "id", nullable = false, updatable = false)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "chapter")
	private List<Lesson> lessons;
	
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Course course;
	
	ChapterDto toDto() {
		return new ChapterDto(id, name);
	}
}
