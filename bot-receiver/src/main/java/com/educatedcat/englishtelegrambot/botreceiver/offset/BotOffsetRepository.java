package com.educatedcat.englishtelegrambot.botreceiver.offset;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface BotOffsetRepository extends CrudRepository<BotOffset, Long> {
	@Modifying
	@Query("""
	       UPDATE bot_offset
	       SET "offset" = :newOffset
	       WHERE id = 1;
	       """)
	void updateCurrentOffset(@Param("newOffset") long newOffset);
}
