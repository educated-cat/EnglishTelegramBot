package com.educatedcat.englishtelegrambot.chapter;

import com.educatedcat.englishtelegrambot.lesson.*;
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
@Table(name = "chapters")
public class Chapter {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToMany
	@JoinTable(name = "chapters_lessons",
	           joinColumns = {
			           @JoinColumn(name = "chapter_id")
	           },
	           inverseJoinColumns = {
			           @JoinColumn(name = "lesson_id")
	           }
	)
	private List<Lesson> lessons;
	
	ChapterDto toDto() {
		return new ChapterDto(id, name);
	}
}