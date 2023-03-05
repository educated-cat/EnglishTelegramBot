package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.user.state.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;

@NamedEntityGraph(name = "User.states",
                  attributeNodes = {
		                  @NamedAttributeNode("states")
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
	
	// Size of button groups
	// TODO: limit size
	@OrderBy("createDate DESC")
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST}, mappedBy = "user")
	private List<UserState> states;
	
	public User(Long id) {
		this.id = id;
		states = new ArrayList<>();
	}
	
	public void addState(UserState state) {
		states.add(state);
	}
}
