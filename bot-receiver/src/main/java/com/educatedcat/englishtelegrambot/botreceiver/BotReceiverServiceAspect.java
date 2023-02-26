package com.educatedcat.englishtelegrambot.botreceiver;

import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

@Slf4j
@Aspect
@Component
public class BotReceiverServiceAspect {
	@Pointcut("execution(public * com.educatedcat.englishtelegrambot.botreceiver.*.*.*(..))")
	public void callAtAllPublicMethods() {
	}
	
	@Before("callAtAllPublicMethods()")
	public void logBefore(JoinPoint joinPoint) {
		if (joinPoint == null || joinPoint.getSignature() == null) {
			return;
		}
		log.debug("Executing {}", joinPoint.getSignature().toShortString());
	}
	
	@After("callAtAllPublicMethods()")
	public void logAfter(JoinPoint joinPoint) {
		if (joinPoint == null || joinPoint.getSignature() == null) {
			return;
		}
		log.debug("Completed {}", joinPoint.getSignature().toShortString());
	}
}
