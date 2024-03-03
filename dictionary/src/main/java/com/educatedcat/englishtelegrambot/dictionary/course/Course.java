package com.educatedcat.englishtelegrambot.dictionary.course;

import com.educatedcat.englishtelegrambot.dictionary.chapter.Chapter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courses_id_seq")
	@SequenceGenerator(name = "courses_id_seq", sequenceName = "courses_id_seq")
	@Column(name = "id")
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "course")
	private List<Chapter> chapters;
	
	CourseDto toDto() {
		return new CourseDto(id, name);
	}
}
