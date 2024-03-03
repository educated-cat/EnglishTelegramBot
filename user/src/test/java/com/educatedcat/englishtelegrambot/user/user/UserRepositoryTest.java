package com.educatedcat.englishtelegrambot.user.user;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@DataJpaTest
@AutoConfigureEmbeddedDatabase
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
