package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.user.state.UserState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

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
	private long id;
	
	// Size of button groups
	// TODO: limit size
	@OrderBy("createDate DESC")
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST}, mappedBy = "user")
	private List<UserState> states;
	
	public User(long id) {
		this.id = id;
		states = new ArrayList<>();
	}
	
	public void addState(UserState state) {
		states.add(state);
	}
}
