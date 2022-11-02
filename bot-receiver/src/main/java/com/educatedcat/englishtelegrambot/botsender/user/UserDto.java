package com.educatedcat.englishtelegrambot.botsender.user;

import com.educatedcat.englishtelegrambot.botsender.button.*;

import java.util.*;

public record UserDto(Long id, MenuButtonType buttonType, UUID buttonTypeId) {
}
