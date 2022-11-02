package com.educatedcat.englishtelegrambot.botsender.word;

import org.springframework.stereotype.*;

@Component
public class WordTextHandlerImpl implements WordTextHandler {
	@Override
	public String formatWord(WordDto word) {
		return String.format("""
		                     %s - %s
		                     %s""", word.name(), word.translation(), word.transcription());
	}
}
