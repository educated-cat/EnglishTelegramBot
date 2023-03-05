package com.educatedcat.englishtelegrambot.dictionary.chapter;

import com.educatedcat.englishtelegrambot.dictionary.course.*;
import com.educatedcat.englishtelegrambot.dictionary.lesson.*;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

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
	
	@OneToMany(mappedBy = "chapter")
	private List<Lesson> lessons;
	
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Course course;
	
	ChapterDto toDto() {
		return new ChapterDto(id, name);
	}
}
