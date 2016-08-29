package com.petception.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by manjtsingh on 7/8/2016.
 */
@Aspect
@Component
public class MetricsAspect {
private static Logger LOGGER = LoggerFactory.getLogger(MetricsAspect.class);

    @Around("@annotation(com.petception.annotation.Metrics)")
    public Object applyMetrics(ProceedingJoinPoint joinPoint) {
        LOGGER.info("Starting method call {}",joinPoint);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            stopWatch.stop();
            LOGGER.info("Ending method call {} - {}ms",joinPoint,stopWatch.getTotalTimeMillis());
        }
        return "error";
    }
}
