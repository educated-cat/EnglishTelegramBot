package com.educatedcat.englishtelegrambot.bot.user.productivity;

import com.educatedcat.englishtelegrambot.bot.word.*;

import java.util.*;

public record UserProductivityDto(long userId, UUID wordId, WordActionType wordActionType) {
}
