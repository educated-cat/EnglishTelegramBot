package com.educatedcat.englishtelegrambot.bot.user;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@DataJpaTest
class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void findByIdNotFound() {
		assertTrue(userRepository.findById(1L).isEmpty());
	}
	
	@Test
	void findById() {
		userRepository.save(new User(1L));
		
		User found = userRepository.findById(1L).get();
		
		assertEquals(1L, found.getId());
	}
}
