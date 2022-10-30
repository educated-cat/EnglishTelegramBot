package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

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
@BatchSize(size = 50)
public class UserProductivity implements Serializable {
	@Id
	@SequenceGenerator(name = "user_productivity_id_generator", sequenceName = "user_productivity_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_productivity_id_generator")
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
