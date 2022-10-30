package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.user.state.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.*;
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