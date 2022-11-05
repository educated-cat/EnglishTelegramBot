package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import com.educatedcat.englishtelegrambot.botreceiver.word.*;

import java.util.*;

public record UserProductivityDto(long userId, UUID wordId, WordActionType wordActionType) {
}
