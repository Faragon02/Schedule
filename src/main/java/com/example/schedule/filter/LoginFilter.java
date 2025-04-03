package com.example.schedule.filter;

import com.example.schedule.common.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {


    private static final String[] WHITE_LIST= {"/", "/users/Login", "/users/signup"};

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = ( HttpServletRequest )request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        log.info("로그인 필터 로직 실행");

        //White_List 포함된 경우 true-> false, 안된경우 해당 로직을 수행
        if(!isWhiteList(requestURI)){
            HttpSession httpSession =  httpRequest.getSession(false);
            if(httpSession == null || httpSession.getAttribute(Const.LOGIN_USER) == null){
                throw new RuntimeException("로그인 해주세요.");
            }
        }

        chain.doFilter(request, response);

    }

    private  boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
