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
		assertEquals(created.getId(), created.getState().getId());
		assertEquals(MenuButtonType.START, created.getState().getButtonType());
		assertNull(created.getState().getButtonTypeId());
	}
}