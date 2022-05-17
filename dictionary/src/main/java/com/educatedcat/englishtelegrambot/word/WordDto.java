package com.educatedcat.englishtelegrambot.word;

import java.util.*;

public record WordDto(UUID id, String name, String transcription) {
	public WordDto(String name, String transcription) {
		this(null, name, transcription);
	}
}
