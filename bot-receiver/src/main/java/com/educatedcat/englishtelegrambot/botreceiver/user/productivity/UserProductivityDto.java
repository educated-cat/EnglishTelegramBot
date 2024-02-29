package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import com.educatedcat.englishtelegrambot.botreceiver.word.WordActionType;

public record UserProductivityDto(long userId, long wordId, WordActionType wordActionType) {
}
