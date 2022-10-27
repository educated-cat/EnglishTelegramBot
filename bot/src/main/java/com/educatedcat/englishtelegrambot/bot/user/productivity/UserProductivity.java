package com.educatedcat.englishtelegrambot.bot.user.productivity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.*;
import java.util.*;

@Getter
@Entity
@Table(name = "user_productivity")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class UserProductivity implements Serializable {
	// TODO: use another DB and use sequence
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, updatable = false)
	private long userId;
	
	@Type(type = "uuid-char")
	@Column(nullable = false, columnDefinition = "VARCHAR(36)")
	private UUID wordId;
	
	/**
	 * Percentage of assimilation
	 */
	@Column(nullable = false)
	private byte progress;
	
	public UserProductivity(Long userId, UUID wordId) {
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
