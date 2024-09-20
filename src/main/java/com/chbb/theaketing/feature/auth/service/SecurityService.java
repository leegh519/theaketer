package com.chbb.theaketing.feature.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chbb.theaketing.feature.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository;

    public String passwordEncode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public void authenticated(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
                password);

        Authentication authentication = authenticationManager.authenticate(token);
        saveContext(authentication);
    }

    public User getUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getUser();
    }

    public void deleteAuthenticate() {
        saveContext(null);
    }

    private void saveContext(Authentication authentication) {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (attributes == null) {
            return;
        }

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, attributes.getRequest(), attributes.getResponse());
    }

}
