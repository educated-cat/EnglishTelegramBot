package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import com.educatedcat.englishtelegrambot.botreceiver.kafka.CustomKafkaProperties;
import com.educatedcat.englishtelegrambot.botreceiver.word.WordActionType;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.time.Duration;
import java.util.List;

import static org.awaitility.Awaitility.*;
import static org.junit.jupiter.api.Assertions.*;

@EmbeddedKafka
@SpringBootTest(properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class UserProductivityServiceTest {
	@Autowired
	private UserProductivityService userProductivityService;
	
	@Autowired
	private CustomKafkaProperties customKafkaProperties;
	
	@Autowired
	private KafkaConsumer<Long, UserProductivityDto> consumer;
	
	@Test
	@SneakyThrows
	void updateUserProductivity() {
		consumer.subscribe(List.of(customKafkaProperties.getProductivity().getTopic().getName()));
		UserProductivityDto productivity = new UserProductivityDto(1L, 1L, WordActionType.KNOW);
		
		userProductivityService.updateUserProductivity(productivity);
		
		await().atMost(Duration.ofSeconds(10)).until(() -> {
			var records = consumer.poll(Duration.ofMillis(100));
			if (records.isEmpty()) {
				return false;
			}
			consumer.close();
			
			assertEquals(1, records.count());
			records.forEach(r -> {
				assertEquals(customKafkaProperties.getProductivity().getTopic().getName(), r.topic());
				assertEquals(productivity, r.value());
				assertEquals(productivity.userId(), r.key());
			});
			return true;
		});
	}
}
