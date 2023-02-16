package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.button.*;
import com.educatedcat.englishtelegrambot.user.kafka.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.test.context.*;

import java.time.*;
import java.util.*;

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
		UserDto user = new UserDto(1L, MenuButtonType.START, UUID.randomUUID());
		
		kafkaTemplate.send(kafkaProperties.getTopic().getName(), user.id(), user);
		
		await().atMost(Duration.ofSeconds(10)).with().pollInterval(Duration.ofMillis(100))
		       .untilAsserted(() -> then(userService).should().saveOrUpdate(user));
	}
	
}
