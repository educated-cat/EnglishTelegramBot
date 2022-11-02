package com.educatedcat.englishtelegrambot.botsender.user.productivity;

import com.educatedcat.englishtelegrambot.botsender.word.*;

import java.util.*;

public record UserProductivityDto(long userId, UUID wordId, WordActionType wordActionType) {
}
