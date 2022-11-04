package com.educatedcat.englishtelegrambot.botreceiver.offset;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.*;

@Data
@Table(name = "bot_offset")
@NoArgsConstructor
public class BotOffset {
	@Id
	private Long id;
	private Long offset;
}
