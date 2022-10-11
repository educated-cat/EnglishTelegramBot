package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.button.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends AbstractTest {
	
	@Test
	void createUserTest() {
		User created = createUser();
		
		assertNotNull(created.getId());
		assertEquals(created.getId(), created.getStates().get(0).getId());
		assertEquals(MenuButtonType.START, created.getStates().get(0).getButtonType());
		assertNull(created.getStates().get(0).getButtonTypeId());
	}
}