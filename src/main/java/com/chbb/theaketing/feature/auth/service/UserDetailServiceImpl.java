package com.chbb.theaketing.feature.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.user.entity.User;
import com.chbb.theaketing.feature.user.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserQueryService userQueryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userQueryService.findByEmail(username);
            return new UserDetailsImpl(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("아이디나 비밀번호가 잘못되었습니다");
        }
    }

}
