package com.educatedcat.englishtelegrambot.userproductivity.word;

import java.util.*;

public record WordProductivityDto(long userId, UUID wordId, WordActionType wordActionType) {
}
