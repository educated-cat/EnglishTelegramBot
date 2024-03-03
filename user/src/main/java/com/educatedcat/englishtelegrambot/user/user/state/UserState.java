package com.educatedcat.englishtelegrambot.user.user.state;

import com.educatedcat.englishtelegrambot.user.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.user.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_states")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@BatchSize(size = 50)
public class UserState {
	@Id
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	private long id;
	
	@Enumerated
	@Column(nullable = false, columnDefinition = "INT2")
	private MenuButtonType buttonType;
	private long buttonTypeId;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public UserState(User user, MenuButtonType buttonType, long buttonTypeId) {
		this.buttonType = buttonType;
		this.buttonTypeId = buttonTypeId;
		this.user = user;
	}
	
}
