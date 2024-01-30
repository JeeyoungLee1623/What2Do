package com.What2Do.What2Do;

import com.What2Do.What2Do.member.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //        로그인에 성공했을때, 추가적인 기능을 구현하고 싶다면 이 메서드에서 구현

        HttpSession session = request.getSession();
            session.setAttribute("my_greeting", authentication.getName() + "님 반갑습니다.");

        // 리다이렉트
        response.sendRedirect("/");
    }
}