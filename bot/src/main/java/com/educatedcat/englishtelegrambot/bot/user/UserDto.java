package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.button.*;

import java.util.*;

public record UserDto(Long id, MenuButtonType buttonType, UUID buttonTypeId) {
}
