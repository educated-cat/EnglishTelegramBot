package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class WordProductivityJob {
	private final WordProductivityService wordProductivityService;
	
	@Scheduled(cron = "#{dictionaryProperties.word.productivity.repeating.updateStatusesCron}")
	public void updateWordProductivityForRepeating() {
		log.info("Start job");
		wordProductivityService.updateStatuses();
		log.info("End job");
	}
}
