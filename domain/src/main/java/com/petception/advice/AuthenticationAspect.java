package com.petception.advice;

import com.petception.auth.UserResponse;
import com.petception.enums.ServerStatus;
import com.petception.request.PetAddRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by manjtsingh on 7/8/2016.
 */
@Aspect
@Component
public class AuthenticationAspect {
private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationAspect.class);
RestTemplate restTemplate = new RestTemplate();

    @Around("@annotation(com.petception.annotation.Authentication) && args(petAddRequest)")
    public Object authenticateUser(ProceedingJoinPoint pjp, PetAddRequest petAddRequest) {
        LOGGER.info("in authenication layer");
        UserResponse response = restTemplate.postForObject("http://localhost:8082/isValidToken","", UserResponse.class);
        if(ServerStatus.FAILED.name().equals(response.getStatus()))
        {

            LOGGER.info("Failed Authentication");
            return "/login";
        }
        try {
            return pjp.proceed(new Object[]{petAddRequest});
        } catch (Throwable throwable) {

        }finally {
            LOGGER.info("Done with method");
        }
        return "/login";
    }
}
