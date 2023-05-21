package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.*;

import java.util.*;

public record UpdateWordProductivityDto(long userId, UUID wordId, WordActionType wordActionType) {
}
