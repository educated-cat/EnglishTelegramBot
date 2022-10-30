package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import com.educatedcat.englishtelegrambot.userproductivity.word.*;

import java.util.*;

public record UserProductivityDto(long userId, UUID wordId, WordActionType wordActionType) {
}
