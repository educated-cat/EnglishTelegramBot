package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.user.state.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	
	@Override
	@Transactional
	public void saveOrUpdate(UserDto userDto) {
		userRepository.findById(userDto.id())
		              .ifPresentOrElse(user -> user.getStates()
		                                           .stream()
		                                           .findFirst()
		                                           .filter(userState -> userState.getButtonType() ==
		                                                                userDto.buttonType())
		                                           .orElseGet(() -> {
			                                           user.addState(new UserState(user, userDto.buttonType(),
			                                                                       userDto.buttonTypeId()));
			                                           return null;
		                                           }),
		                               () -> createUser(userDto));
	}
	
	private void createUser(UserDto userDto) {
		User user = userRepository.save(new User(userDto.id()));
		UserState state = new UserState(user, userDto.buttonType(), userDto.buttonTypeId());
		user.addState(state);
	}
}
