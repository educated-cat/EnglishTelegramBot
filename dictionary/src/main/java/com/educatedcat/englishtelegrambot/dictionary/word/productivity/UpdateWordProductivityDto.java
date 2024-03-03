package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.WordActionType;

public record UpdateWordProductivityDto(long userId, long wordId, WordActionType wordActionType) {
}
