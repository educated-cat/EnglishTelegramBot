package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@MockBeans({
		@MockBean(DictionaryProperties.class),
		@MockBean(DictionaryConfig.class),
		@MockBean(WebClient.class),
		@MockBean(TelegramBotsApi.class)
})
@SuppressWarnings("OptionalGetWithoutIsPresent")
@SpringBootTest
class UserStatesTest {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	void findById() {
		var buttonId = UUID.randomUUID();
		userService.saveOrUpdate(1L, MenuButtonType.LESSON, buttonId);
		var created = userRepository.findById(1L).get();
		
		var states = created.getStates();
		
		assertEquals(1, states.size());
		assertNotNull(states.get(0).getId());
		assertEquals(MenuButtonType.LESSON, states.get(0).getButtonType());
		assertEquals(buttonId, states.get(0).getButtonTypeId());
		assertNotNull(states.get(0).getCreateDate());
	}
}
