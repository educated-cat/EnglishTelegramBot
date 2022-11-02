package com.educatedcat.englishtelegrambot.botsender.word;

import com.educatedcat.englishtelegrambot.botsender.keyboard.*;

public interface WordTextFactory {
	String buildText(KeyboardEntry entry);
}
