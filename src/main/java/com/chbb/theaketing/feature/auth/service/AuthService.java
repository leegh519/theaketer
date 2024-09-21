package com.chbb.theaketing.feature.auth.service;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.auth.dto.AuthDto;
import com.chbb.theaketing.feature.auth.dto.SignUpDto;
import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.common.redis.RedisRepository;
import com.chbb.theaketing.feature.user.dto.UserDto;
import com.chbb.theaketing.feature.user.entity.User;
import com.chbb.theaketing.feature.user.service.UserCommandService;
import com.chbb.theaketing.feature.user.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RedisRepository redisRepository;

    private final SecurityService securityService;

    private final UserCommandService userCommandService;

    private final UserQueryService userQueryService;

    public UserDto.UserInfo login(AuthDto.LoginReq req) throws Exception {
        securityService.authenticated(req.getEmail(), req.getPassword());

        return getUserDto(req.getEmail());
    }

    public UserDto.UserInfo signUp(SignUpDto.SignUpReq req) throws Exception {
        isAuthChecked(req.getEmail());
        // 비밀번호 암호화
        String encodePassword = securityService.passwordEncode(req.getPassword());

        // db 저장
        User user = User.builder()
                .email(req.getEmail())
                .name(req.getName())
                .password(encodePassword)
                .phone(req.getPhone())
                .build();
        userCommandService.insert(user);

        // redis 인증정보 삭제
        redisRepository.deleteValue(req.getEmail());

        // security context 저장
        securityService.authenticated(req.getEmail(), req.getPassword());

        return getUserDto(req.getEmail());
    }

    private void isAuthChecked(String email) throws Exception {
        String isChecked = redisRepository.getValue(email);
        if (isChecked == null) {
            throw new TheaketingException(ErrorCode.NOT_EMAIL_AUTHENTICATE);
        }
        if (!Boolean.parseBoolean(isChecked)) {
            throw new TheaketingException(ErrorCode.NOT_EMAIL_AUTHENTICATE);
        }
    }

    private UserDto.UserInfo getUserDto(String email) throws Exception {
        // 유저정보 조회
        User createdUser = userQueryService.findByEmail(email);

        UserDto.UserInfo userDto = UserDto.UserInfo.builder()
                .id(createdUser.getId())
                .email(createdUser.getEmail())
                .name(createdUser.getName())
                .phone(createdUser.getPhone())
                .build();
        return userDto;
    }

    public void logout() {
        securityService.deleteAuthenticate();
    }

    public void emailDuplicateCheck(String email) throws Exception {
        if (userQueryService.existByEmail(email)) {
            throw new TheaketingException(ErrorCode.EMAIL_DUPLICATE);
        }
    }
}
