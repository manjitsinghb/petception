package com.petception.advice;

import com.petception.user.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by manjtsingh on 8/22/2016.
 */
@Aspect
@Component
public class AuthenticationAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationAspect.class);
    private RestTemplate restTemplate = new RestTemplate();

    @Around("@annotation(com.petception.annotation.Authentication) && args(request,response,model)")
    public Object applyAuthentication(ProceedingJoinPoint joinPoint, HttpServletRequest request, HttpServletResponse response, Model model) {
        LOGGER.info("Starting authentication call {}",joinPoint);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        if(request.getParameter("username")!=null && request.getParameter("password")!=null)
        {
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            String token = restTemplate.postForObject("http://poauth:8082/authenticate",user,String.class);
            user.setPassword(null);
            if(token == null)
            {
                LOGGER.info("Failed authentication using username/password");
                return "login";
            }
            else {
                try {
                    Cookie cookie = new Cookie("token", URLEncoder.encode(token, "UTF-8"));
                    response.addCookie(cookie);
                    request.setAttribute("username",user.getUsername());
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }finally {
                    stopWatch.stop();
                    LOGGER.info("Ending authentication call {} - {}ms",joinPoint,stopWatch.getTotalTimeMillis());
                }
            }
        }
        LOGGER.info("Auth Failed using Username / password, trying token");
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies)
        {
            if("token".equals(cookie.getName()))
            {
                String token = null;
                User user=null;
                try {
                    token = URLDecoder.decode(cookie.getValue(),"UTF-8");
                    user = restTemplate.postForObject("http://poauth:8082/isValidUser",token,User.class);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //use template to validate
                if(user!=null)
                {
                    request.setAttribute("username",user.getUsername());
                    try {
                        return joinPoint.proceed();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }finally {
                        stopWatch.stop();
                        LOGGER.info("Ending authentication call {} - {}ms",joinPoint,stopWatch.getTotalTimeMillis());
                    }
                }
                return "login";
            }
        }
            stopWatch.stop();
            LOGGER.info("Ending authentication call {} - {}ms",joinPoint,stopWatch.getTotalTimeMillis());
            return "login";
    }

}
