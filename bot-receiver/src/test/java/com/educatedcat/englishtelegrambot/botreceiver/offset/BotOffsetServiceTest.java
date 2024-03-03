package com.educatedcat.englishtelegrambot.botreceiver.offset;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureEmbeddedDatabase
@SpringBootTest
class BotOffsetServiceTest {
	@Autowired
	private BotOffsetService botOffsetService;
	
	@Test
	void getCurrentOffset() {
		BotOffset offset = botOffsetService.getCurrentOffset().orElseThrow();
		
		assertEquals(0, offset.getOffset());
	}
	
	@Test
	void updateCurrentOffset() {
		botOffsetService.updateCurrentOffset(12345L);
		
		BotOffset updatedOffset = botOffsetService.getCurrentOffset().orElseThrow();
		assertEquals(12345L, updatedOffset.getOffset());
	}
}
