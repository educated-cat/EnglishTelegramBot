package com.educatedcat.englishtelegrambot.bot.user.productivity;

import org.junit.jupiter.api.*;
import org.springframework.test.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserProductivityTest {
	
	@Test
	void increaseProgress() {
		UserProductivity productivity = new UserProductivity();
		
		productivity.increaseProgress();
		
		assertTrue(productivity.getProgress() > 0);
	}
	
	@Test
	void increaseProgressMultipleTimes() {
		UserProductivity productivity = new UserProductivity();
		
		for (int i = 0; i < 100; i++) {
			productivity.increaseProgress();
		}
		
		assertTrue(productivity.getProgress() > 0);
		assertTrue(productivity.getProgress() <= 100);
	}
	
	@Test
	void decreaseProgressUnlearnedWordProductivity() {
		UserProductivity productivity = new UserProductivity();
		
		productivity.decreaseProgress();
		
		assertEquals(0, productivity.getProgress());
	}
	
	@Test
	void decreaseProgressFullyLearnedWordProductivity() {
		UserProductivity productivity = new UserProductivity();
		ReflectionTestUtils.setField(productivity, "progress", (byte) 100);
		
		productivity.decreaseProgress();
		
		assertTrue(productivity.getProgress() > 0);
		assertTrue(productivity.getProgress() < 100);
	}
	
	@Test
	void decreaseProgressFullyLearnedWordProductivityToZero() {
		UserProductivity productivity = new UserProductivity();
		ReflectionTestUtils.setField(productivity, "progress", (byte) 100);
		
		for (int i = 0; i < 100; i++) {
			productivity.decreaseProgress();
		}
		
		assertEquals(0, productivity.getProgress());
	}
}
