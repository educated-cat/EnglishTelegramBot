package com.educatedcat.englishtelegrambot.botreceiver.offset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotOffsetServiceImpl implements BotOffsetService {
	private final BotOffsetRepository botOffsetRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<BotOffset> getCurrentOffset() {
		return botOffsetRepository.findById(1L);
	}
	
	@Override
	@Transactional
	public void updateCurrentOffset(long newOffset) {
		botOffsetRepository.updateCurrentOffset(newOffset);
	}
}
