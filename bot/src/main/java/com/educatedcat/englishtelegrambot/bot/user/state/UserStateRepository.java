package com.educatedcat.englishtelegrambot.bot.user.state;

import org.springframework.data.jpa.repository.*;

interface UserStateRepository extends JpaRepository<UserState, Long> {
}