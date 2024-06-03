package com.estate.back.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.estate.back.common.object.CustomOAuth2User;
import com.estate.back.provider.JwtProvider;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    @Value("${deploy.env}")
    private String DEPLOY_ENV;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String userId = oAuth2User.getName();

        String token = jwtProvider.create(userId);

        if (DEPLOY_ENV.equals("production"))
            response.sendRedirect("http://13.124.227.172:3000/sns/" + token + "/36000");
        else
            response.sendRedirect("http://localhost:3000/sns/" + token + "/36000");

    }

}
