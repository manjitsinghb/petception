package com.petception.app.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by manjtsingh on 7/8/2016.
 */
@Aspect
@Component
public class AuthenticationAspect {
private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationAspect.class);

    @Before("@annotation(com.petception.app.annotation.Authentication)")
    public void authenticateUser() {
        LOGGER.info("in authenication layer");

    }
}
