package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	@Setter
	@Column(nullable = false)
	private byte progress;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime creationTimestamp;
	
	@Setter
	private LocalDateTime learnTimestamp;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RepeatingStatus repeatingStatus;
	
	@Column(nullable = false)
	private int failedAttemptsCount;
	
	@Column(nullable = false)
	private int successAttemptsCount;
	
	@Column(nullable = false)
	private int successAttemptsDayCount;
	
	public WordProductivity(Long userId, long wordId) {
		this.userId = userId;
		this.wordId = wordId;
		progress = 0;
		creationTimestamp = LocalDateTime.now();
		failedAttemptsCount = 0;
		successAttemptsCount = 0;
		successAttemptsDayCount = 0;
	}
	
	// TODO: move business logic to another class
	public void increaseProgress() {
		progress += 25;
		if (progress > 100) {
			progress = 100;
		}
		if (progress == 100) {
			learnTimestamp = LocalDateTime.now();
		}
	}
	
	public void decreaseProgress() {
		progress -= 25;
		if (progress < 0) {
			progress = 0;
		}
	}
}
