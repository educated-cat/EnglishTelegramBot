package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.button.*;

import java.util.*;

public record UserDto(Long id, MenuButtonType buttonType, UUID buttonTypeId) {
}
