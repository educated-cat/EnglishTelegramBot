package com.educatedcat.englishtelegrambot.bot.user.state;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.user.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user_states")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class UserState {
	// TODO: use SEQUENCE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated
	@Column(nullable = false, columnDefinition = "TINYINT")
	private MenuButtonType buttonType;
	
	@Type(type = "uuid-char")
	@Column(columnDefinition = "VARCHAR(36)")
	private UUID buttonTypeId;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public UserState(User user, MenuButtonType buttonType, UUID buttonTypeId) {
		this.buttonType = buttonType;
		this.buttonTypeId = buttonTypeId;
		this.user = user;
	}
	
}
