package com.educatedcat.englishtelegrambot.botreceiver.user;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.kafka.CustomKafkaProperties;
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
class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomKafkaProperties customKafkaProperties;
	
	@Autowired
	private KafkaConsumer<Long, UserDto> consumer;
	
	@Test
	@SneakyThrows
	void saveOrUpdate() {
		consumer.subscribe(List.of(customKafkaProperties.getUser().getTopic().getName()));
		UserDto user = new UserDto(1L, MenuButtonType.START, 1L);
		
		userService.saveOrUpdate(user);
		
		await().atMost(Duration.ofSeconds(10)).until(() -> {
			var records = consumer.poll(Duration.ofMillis(100));
			if (records.isEmpty()) {
				return false;
			}
			consumer.close();
			
			assertEquals(1, records.count());
			records.forEach(r -> {
				assertEquals(customKafkaProperties.getUser().getTopic().getName(), r.topic());
				assertEquals(user, r.value());
				assertEquals(user.id(), r.key());
			});
			return true;
		});
	}
}
