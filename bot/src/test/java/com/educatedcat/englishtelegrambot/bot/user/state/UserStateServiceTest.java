package com.educatedcat.englishtelegrambot.bot.user.state;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.user.*;
import org.junit.jupiter.api.*;

import java.util.*;

class UserStateServiceTest extends AbstractTest {
	@Test
	void updateState() {
		UUID courseId = UUID.randomUUID();
		User created = createUser();

//		userStateService.updateState(created.getStates().get(0).getId(), MenuButtonType.COURSE, courseId);

//		User updated = userService.findById(created.getId()).orElseThrow();
//		assertEquals(MenuButtonType.COURSE, updated.getStates().get(0).getButtonType());
//		assertEquals(courseId, updated.getStates().get(0).getButtonTypeId());
	}
}