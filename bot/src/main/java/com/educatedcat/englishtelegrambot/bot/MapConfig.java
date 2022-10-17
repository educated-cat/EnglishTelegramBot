package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.command.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class MapConfig {
	@Bean
	public EnumMap<BotCommandType, BotCommand> commandMap(BotCommand startBotCommand) {
		return new EnumMap<>(BotCommandType.class) {{
			put(BotCommandType.START, startBotCommand);
		}};
	}
	
	// TODO: use List<Handler>
	@Bean
	public EnumMap<MenuButtonType, CallbackButtonHandler> buttonMap(CourseCallbackButtonHandler courseButtonHandler,
	                                                                ChapterCallbackButtonHandler chapterButtonHandler,
	                                                                LessonCallbackButtonHandler lessonButtonHandler,
	                                                                ByCourseCallbackButtonHandler byCourseButtonHandler) {
		return new EnumMap<>(MenuButtonType.class) {{
			put(MenuButtonType.COURSE, courseButtonHandler);
			put(MenuButtonType.CHAPTER, chapterButtonHandler);
			put(MenuButtonType.LESSON, lessonButtonHandler);
			put(MenuButtonType.BY_COURSE, byCourseButtonHandler);
		}};
	}
}
