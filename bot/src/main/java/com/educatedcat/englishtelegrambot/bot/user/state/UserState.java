package com.educatedcat.englishtelegrambot.bot.user.state;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.user.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user_states")
@DynamicUpdate
@NoArgsConstructor
public class UserState {
	@Id
	@GenericGenerator(name = "assigned", strategy = "org.hibernate.id.Assigned")
	@GeneratedValue(generator = "assigned")
	private Long id;
	
	@Enumerated
	@Column(nullable = false, columnDefinition = "TINYINT")
	private MenuButtonType buttonType;
	
	@Type(type = "uuid-char")
	@Column(columnDefinition = "VARCHAR(36)")
	private UUID buttonTypeId;
	
	@MapsId
	@JoinColumn(name = "id")
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	public UserState(User user, MenuButtonType buttonType, UUID buttonTypeId) {
		this.id = user.getId();
		this.buttonType = buttonType;
		this.buttonTypeId = buttonTypeId;
		this.user = user;
	}
	
	void update(MenuButtonType buttonType, UUID buttonTypeId) {
		this.buttonType = buttonType;
		this.buttonTypeId = buttonTypeId;
	}
}
