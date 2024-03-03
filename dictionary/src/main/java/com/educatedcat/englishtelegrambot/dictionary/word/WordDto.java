package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.translation.Language;

public record WordDto(Long id, Long index, String name, String transcription, String translation,
                      Language language) {
}
