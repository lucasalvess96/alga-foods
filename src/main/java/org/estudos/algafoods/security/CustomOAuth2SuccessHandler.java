package org.estudos.algafoods.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class CustomOAuth2SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    public CustomOAuth2SuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String email = oauthToken.getPrincipal().getAttribute("email");
        String jwt = jwtUtil.generateToken(email);

        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        String targetUrl = (savedRequest != null) ? savedRequest.getRedirectUrl() : "/";

        String redirectUrl = UriComponentsBuilder
                .fromUriString(targetUrl)
                .queryParam("token", jwt)
                .build()
                .toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
