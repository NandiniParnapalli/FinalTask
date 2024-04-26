package com.kog.emp.config;

import com.kog.emp.entity.Token;
import com.kog.emp.repository.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader=request.getHeader("Authorization");
        String token=null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

        }
        // get stored token from database
        Token storedToken=tokenRepository.findByToken(token).orElse(null);
       // invalidate the token =>make logout=true
        if(token!=null)
        {
            storedToken.setLoggedOut(true);
            tokenRepository.save(storedToken);
        }

    }
}
