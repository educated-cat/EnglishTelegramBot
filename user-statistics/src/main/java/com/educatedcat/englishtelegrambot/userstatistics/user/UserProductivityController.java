package com.educatedcat.englishtelegrambot.userstatistics.user;

import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class UserProductivityController {
	private final UserProductivityFacade userProductivityFacade;
	
	@GetMapping("/{userId}")
	public UserProductivityDto getUserProductivity(@PathVariable long userId) {
		return userProductivityFacade.prepareUserProductivity(userId);
	}
}
