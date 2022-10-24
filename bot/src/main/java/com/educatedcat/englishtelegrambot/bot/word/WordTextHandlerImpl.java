package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
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
