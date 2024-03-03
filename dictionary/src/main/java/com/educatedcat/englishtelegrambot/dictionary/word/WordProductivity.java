package com.educatedcat.englishtelegrambot.dictionary.word;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

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
	private long id;
	
	@Column(nullable = false, updatable = false)
	private long userId;
	
	@Column(nullable = false)
	private long wordId;
	
	/**
	 * Percentage of assimilation
	 */
	@Column(nullable = false)
	private byte progress;
	
	public WordProductivity(Long userId, long wordId) {
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
