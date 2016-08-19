package com.petception.advice;

import com.petception.annotation.Authentication;
import com.petception.auth.UserRequest;
import com.petception.auth.UserResponse;
import com.petception.enums.ServerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by manjtsingh on 7/25/2016.
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)o;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Authentication.class))
        {
            LOGGER.info("in authenication layer");
            String username = httpServletRequest.getParameter("username");
            String password = httpServletRequest.getParameter("password");
            UserRequest request = new UserRequest();
            request.setUsername(username);
            request.setPassword(password);
            Cookie[] cookies = httpServletRequest.getCookies();
            if(cookies==null || cookies.length==0)
            {
                return false;
            }
            String token = null;
            for(Cookie cookie : cookies)
            {
                if("token".equalsIgnoreCase(cookie.getName()))
                {
                    token = cookie.getValue();
                }
            }
            UserResponse response =null;
            if(username!=null && password!=null) {
                response = restTemplate.postForObject("http://poauth:8082/authenticate", request, UserResponse.class);
            }
            else if(token!=null)
            {
                response = restTemplate.postForObject("http://poauth:8082/isValidToken", token, UserResponse.class);
            }
            else{
                return false;
            }
            if(ServerStatus.FAILED.equals(response.getStatus()))
            {
                LOGGER.info("Failed Authentication");
                return false;
            }
            httpServletRequest.setAttribute("username",response.getUserInfo().getUsername());
            Cookie cookie = new Cookie("token",response.getToken());
            httpServletResponse.addCookie(cookie);
            return true;
        }
        LOGGER.info("Skipped Authentication");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
