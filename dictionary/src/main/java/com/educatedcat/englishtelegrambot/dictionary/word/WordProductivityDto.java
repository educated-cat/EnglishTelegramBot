package com.educatedcat.englishtelegrambot.dictionary.word;

import java.util.*;

public record WordProductivityDto(long userId, UUID wordId, WordActionType wordActionType) {
}
