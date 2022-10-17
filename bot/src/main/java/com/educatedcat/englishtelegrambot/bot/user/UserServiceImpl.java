package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.user.state.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	
	@Override
	@Transactional
	public void saveOrUpdate(Long id, MenuButtonType buttonType, UUID buttonTypeId) {
		userRepository.findById(id)
		              .ifPresentOrElse(user -> user.getStates()
		                                           .stream()
		                                           .findFirst()
		                                           .filter(userState -> userState.getButtonType() == buttonType)
		                                           .ifPresentOrElse(userState -> {
		                                                            },
		                                                            () -> user.addState(new UserState(user, buttonType,
		                                                                                              buttonTypeId))),
		                               () -> createUser(id, buttonType));
	}
	
	private void createUser(Long id, MenuButtonType buttonType) {
		User user = userRepository.save(new User(id));
		UserState state = new UserState(user, buttonType, null);
		user.addState(state);
	}
}
