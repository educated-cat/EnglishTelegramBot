package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.button.*;
import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureEmbeddedDatabase
@SuppressWarnings("OptionalGetWithoutIsPresent")
@SpringBootTest(properties = {"spring.main.lazy-initialization=true"})
class UserServiceTest {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	void save() {
		var id = 123L;
		var buttonType = MenuButtonType.START;
		var buttonId = UUID.randomUUID();
		
		userService.saveOrUpdate(new UserDto(id, buttonType, buttonId));
		
		User saved = userRepository.findById(id).get();
		assertEquals(id, saved.getId());
		assertEquals(1, saved.getStates().size());
		assertEquals(buttonType, saved.getStates().get(0).getButtonType());
		assertEquals(buttonId, saved.getStates().get(0).getButtonTypeId());
	}
	
	@Test
	void update() {
		var id = 1234L;
		var buttonType = MenuButtonType.LESSON;
		var buttonId = UUID.randomUUID();
		userService.saveOrUpdate(new UserDto(id, MenuButtonType.START, UUID.randomUUID()));
		
		userService.saveOrUpdate(new UserDto(id, buttonType, buttonId));
		
		User saved = userRepository.findById(id).get();
		assertEquals(id, saved.getId());
		assertEquals(2, saved.getStates().size());
		assertEquals(buttonType, saved.getStates().get(0).getButtonType());
		assertEquals(buttonId, saved.getStates().get(0).getButtonTypeId());
	}
}
