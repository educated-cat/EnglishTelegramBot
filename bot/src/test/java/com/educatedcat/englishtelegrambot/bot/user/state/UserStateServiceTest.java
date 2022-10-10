package com.educatedcat.englishtelegrambot.bot.user.state;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserStateServiceTest extends AbstractTest {
	@Autowired
	private UserStateService userStateService;
	
	@Test
	void updateState() {
		UUID courseId = UUID.randomUUID();
		User created = createUser();
		
		userStateService.updateState(created.getId(), MenuButtonType.COURSE, courseId);
		
		User updated = userService.findById(created.getId()).orElseThrow();
		assertEquals(MenuButtonType.COURSE, updated.getState().getButtonType());
		assertEquals(courseId, updated.getState().getButtonTypeId());
	}
}