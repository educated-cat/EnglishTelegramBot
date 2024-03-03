package com.educatedcat.englishtelegrambot.userstatistics.user;

import io.micrometer.tracing.annotation.ContinueSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class UserProductivityController {
	private final UserProductivityFacade userProductivityFacade;
	
	@ContinueSpan(log = "Get statistics by user ID")
	@GetMapping("/{userId}")
	public UserProductivityDto getUserProductivity(@PathVariable long userId) {
		return userProductivityFacade.prepareUserProductivity(userId);
	}
}
