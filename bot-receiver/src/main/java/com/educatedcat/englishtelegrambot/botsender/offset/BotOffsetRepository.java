package com.educatedcat.englishtelegrambot.botsender.offset;

import org.springframework.data.jdbc.repository.query.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;

interface BotOffsetRepository extends CrudRepository<BotOffset, Long> {
	@Modifying
	@Query("""
	       UPDATE bot_offset
	       SET "offset" = :newOffset
	       WHERE id = 1;
	       """)
	void updateCurrentOffset(@Param("newOffset") long newOffset);
}
