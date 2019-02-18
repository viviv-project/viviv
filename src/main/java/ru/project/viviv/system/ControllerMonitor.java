package ru.project.viviv.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerMonitor {

    private static final Logger log = LogManager.getLogger(ControllerMonitor.class);

    @AfterReturning("execution(* ru.project.viviv.controller..*.*(..))")
    public void monitorControllerFinishExecution(final JoinPoint joinPoint) {
        log.info("Completed: {}", joinPoint);
    }
}