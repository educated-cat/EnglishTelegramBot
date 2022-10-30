package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.translation.*;

import java.util.*;

public record WordDto(UUID id, Long index, String name, String transcription, String translation, Language language) {
}
