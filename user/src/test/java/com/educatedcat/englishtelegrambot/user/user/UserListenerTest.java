package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.user.kafka.KafkaProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.time.Duration;

import static org.awaitility.Awaitility.*;
import static org.mockito.BDDMockito.*;

@EmbeddedKafka
@SpringBootTest(properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class UserListenerTest {
	@Autowired
	private KafkaTemplate<Long, UserDto> kafkaTemplate;
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@MockBean
	private UserService userService;
	
	@SuppressWarnings("unused")
	@Autowired
	private UserListener userListener;
	
	@Test
	void saveUserState() {
		UserDto user = new UserDto(1L, MenuButtonType.START, 1L);
		
		kafkaTemplate.send(kafkaProperties.getTopic().getName(), user.id(), user);
		
		await().atMost(Duration.ofSeconds(10)).with().pollInterval(Duration.ofMillis(100))
		       .untilAsserted(() -> then(userService).should().saveOrUpdate(user));
	}
	
}
