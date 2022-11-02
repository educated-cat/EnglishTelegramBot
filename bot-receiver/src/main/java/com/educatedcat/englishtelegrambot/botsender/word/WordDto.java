package com.educatedcat.englishtelegrambot.botsender.word;

import com.educatedcat.englishtelegrambot.botsender.dictionary.*;

import java.util.*;

public record WordDto(UUID id, String name, String transcription, String translation, Language language)
		implements ButtonMarker {
}
