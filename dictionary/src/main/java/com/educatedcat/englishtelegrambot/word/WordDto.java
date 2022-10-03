package com.educatedcat.englishtelegrambot.word;

import com.educatedcat.englishtelegrambot.translation.*;

import java.util.*;

public record WordDto(UUID id, String name, String transcription, String translation, Language language) {
}
