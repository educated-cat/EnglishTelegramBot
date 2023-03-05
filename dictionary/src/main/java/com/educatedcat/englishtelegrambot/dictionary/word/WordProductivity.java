package com.educatedcat.englishtelegrambot.dictionary.word;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.io.*;
import java.util.*;

@Getter
@Entity
@Table(name = "word_productivity")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@BatchSize(size = 50)
public class WordProductivity implements Serializable {
	@Id
	@SequenceGenerator(name = "word_productivity_id_generator", sequenceName = "word_productivity_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_productivity_id_generator")
	private Long id;
	
	@Column(nullable = false, updatable = false)
	private long userId;
	
	@Column(nullable = false)
	private UUID wordId;
	
	/**
	 * Percentage of assimilation
	 */
	@Column(nullable = false)
	private byte progress;
	
	public WordProductivity(Long userId, UUID wordId) {
		this.userId = userId;
		this.wordId = wordId;
		progress = 0;
	}
	
	public void increaseProgress() {
		progress += 25;
		if (progress > 100) {
			progress = 100;
		}
	}
	
	public void decreaseProgress() {
		progress -= 25;
		if (progress < 0) {
			progress = 0;
		}
	}
}
