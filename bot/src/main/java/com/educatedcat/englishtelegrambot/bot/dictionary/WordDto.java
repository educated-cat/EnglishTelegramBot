package com.educatedcat.englishtelegrambot.bot.dictionary;

import java.util.*;

public record WordDto(UUID id, String name, String transcription, String translation, Language language)
		implements ButtonMarker {
}
