package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.user.state.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.*;

@NamedEntityGraph(name = "User.state",
                  attributeNodes = {
		                  @NamedAttributeNode("state")
                  })
@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
	@Id
	@GenericGenerator(name = "assigned", strategy = "org.hibernate.id.Assigned")
	@GeneratedValue(generator = "assigned")
	private Long id;
	
	// TODO: fix cascade MERGE, use PERSIST instead
	@JoinColumn(name = "id", nullable = false, unique = true)
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, mappedBy = "user")
	private UserState state;
	
	public User(Long id, MenuButtonType buttonType, UUID buttonTypeId) {
		this.id = id;
		state = new UserState(this, buttonType, buttonTypeId);
	}
}
