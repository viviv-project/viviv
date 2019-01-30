package ru.project.viviv.system;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLogging {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogging.class);

    @Around("execution(* ru.project.viviv.controller..*.*(..))")
    public Object logControllerExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        LOGGER.info("Log Around: <<< {} >>> : Before Method Execution", joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        LOGGER.info("Log Around: <<< {} >>> : After Method Execution", joinPoint.getSignature().getName());

        return result;
    }

}
