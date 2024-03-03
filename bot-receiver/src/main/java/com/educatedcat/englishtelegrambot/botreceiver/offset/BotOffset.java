package com.educatedcat.englishtelegrambot.botreceiver.offset;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "bot_offset")
@NoArgsConstructor
public class BotOffset {
	@Id
	private Long id;
	private Long offset;
}
