package com.educatedcat.englishtelegrambot.botreceiver.offset;

import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

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
