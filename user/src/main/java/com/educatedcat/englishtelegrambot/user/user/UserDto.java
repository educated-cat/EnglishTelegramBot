package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.button.MenuButtonType;

public record UserDto(long id, MenuButtonType buttonType, long buttonTypeId) {
}
