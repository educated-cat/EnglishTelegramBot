package com.educatedcat.englishtelegrambot.dictionary.course;

import com.educatedcat.englishtelegrambot.dictionary.chapter.*;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "course")
	private List<Chapter> chapters;
	
	CourseDto toDto() {
		return new CourseDto(id, name);
	}
}
