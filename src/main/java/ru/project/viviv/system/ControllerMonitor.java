package ru.project.viviv.system;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogging.class);

    @AfterReturning("execution(* ru.project.viviv.controller..*.*(..))")
    public void monitorControllerFinishExecution(final JoinPoint joinPoint) {
        LOGGER.info("Completed: " + joinPoint);
    }
}