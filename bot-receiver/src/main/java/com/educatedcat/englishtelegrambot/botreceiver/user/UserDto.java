package com.educatedcat.englishtelegrambot.botreceiver.user;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;

public record UserDto(Long id, MenuButtonType buttonType, Long buttonTypeId) {
}
