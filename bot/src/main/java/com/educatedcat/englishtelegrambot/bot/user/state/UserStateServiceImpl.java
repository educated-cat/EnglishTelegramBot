package com.educatedcat.englishtelegrambot.bot.user.state;

import com.educatedcat.englishtelegrambot.bot.button.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserStateServiceImpl implements UserStateService {
	private final UserStateRepository userStateRepository;
	
	@Override
	@Transactional
	public void updateState(Long id, MenuButtonType buttonType, UUID buttonTypeId) {
		userStateRepository.findById(id)
		                   .ifPresentOrElse(state -> state.update(buttonType, buttonTypeId),
		                                    () -> log.error("UserState not found, id={}", id));
		
	}
}
