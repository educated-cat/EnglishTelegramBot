package com.educatedcat.englishtelegrambot.dictionary.word;

public record UpdateWordProductivityDto(long userId, long wordId, WordActionType wordActionType) {
}
