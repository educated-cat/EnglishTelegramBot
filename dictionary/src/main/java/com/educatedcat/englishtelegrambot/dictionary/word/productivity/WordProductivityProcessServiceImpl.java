package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WordProductivityProcessServiceImpl implements WordProductivityProcessService {
	@Override
	public void increaseProgress(WordProductivity productivity) {
		byte progress = productivity.getProgress();
		progress += 25;
		if (progress > 100) {
			progress = 100;
		}
		if (progress == 100) {
			productivity.setLearnTimestamp(LocalDateTime.now());
		}
		productivity.setProgress(progress);
	}
	
	@Override
	public void decreaseProgress(WordProductivity productivity) {
		byte progress = productivity.getProgress();
		progress -= 25;
		if (progress < 0) {
			progress = 0;
		}
		productivity.setProgress(progress);
	}
}
