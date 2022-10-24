package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;

public interface WordTextFactory {
	String buildText(KeyboardEntry entry);
}
