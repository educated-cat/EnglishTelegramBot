package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class WordProductivityProcessServiceTest {
	@Test
	void increaseProgress() {
		WordProductivity productivity = new WordProductivity();
		
		productivity.increaseProgress();
		
		assertTrue(productivity.getProgress() > 0);
	}
	
	@Test
	void increaseProgressMultipleTimes() {
		WordProductivity productivity = new WordProductivity();
		
		for (int i = 0; i < 100; i++) {
			productivity.increaseProgress();
		}
		
		assertTrue(productivity.getProgress() > 0);
		assertTrue(productivity.getProgress() <= 100);
	}
	
	@Test
	void decreaseProgressUnlearnedWordProductivity() {
		WordProductivity productivity = new WordProductivity();
		
		productivity.decreaseProgress();
		
		assertEquals(0, productivity.getProgress());
	}
	
	@Test
	void decreaseProgressFullyLearnedWordProductivity() {
		WordProductivity productivity = new WordProductivity();
		ReflectionTestUtils.setField(productivity, "progress", (byte) 100);
		
		productivity.decreaseProgress();
		
		assertTrue(productivity.getProgress() > 0);
		assertTrue(productivity.getProgress() < 100);
	}
	
	@Test
	void decreaseProgressFullyLearnedWordProductivityToZero() {
		WordProductivity productivity = new WordProductivity();
		ReflectionTestUtils.setField(productivity, "progress", (byte) 100);
		
		for (int i = 0; i < 100; i++) {
			productivity.decreaseProgress();
		}
		
		assertEquals(0, productivity.getProgress());
	}
}