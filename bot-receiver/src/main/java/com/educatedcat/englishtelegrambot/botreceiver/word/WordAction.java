package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;

import java.util.*;

public record WordAction(UUID id, WordActionType actionType) implements ButtonMarker {
}
