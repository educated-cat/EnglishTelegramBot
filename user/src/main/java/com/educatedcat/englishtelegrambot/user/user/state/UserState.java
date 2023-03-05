package com.educatedcat.englishtelegrambot.user.user.state;

import com.educatedcat.englishtelegrambot.user.button.*;
import com.educatedcat.englishtelegrambot.user.user.*;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.*;
import java.util.*;

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
	private Long id;
	
	@Enumerated
	@Column(nullable = false, columnDefinition = "INT2")
	private MenuButtonType buttonType;
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
