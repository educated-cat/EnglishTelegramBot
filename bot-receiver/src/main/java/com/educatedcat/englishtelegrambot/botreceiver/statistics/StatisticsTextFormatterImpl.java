package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class StatisticsTextFormatterImpl implements StatisticsTextFormatter {
	private final MessageSource messageSource;
	
	@Override
	public String format(UserProductivityDto userProductivityDto) {
		// TODO: add on error resume action to WebClient
		var wordProductivity = userProductivityDto.wordProductivity();
		return messageSource.getMessage("statistics.words",
		                                new Object[]{
				                                wordProductivity.fullyLearnedWords(),
				                                wordProductivity.partlyLearnedWords(),
				                                wordProductivity.notLearnedWords()
		                                }, Locale.ENGLISH);
	}
}
