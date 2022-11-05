package com.educatedcat.englishtelegrambot.botreceiver.user;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;

import java.util.*;

public record UserDto(Long id, MenuButtonType buttonType, UUID buttonTypeId) {
}
