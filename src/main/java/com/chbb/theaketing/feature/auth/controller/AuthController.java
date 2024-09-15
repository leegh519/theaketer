package com.chbb.theaketing.feature.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chbb.theaketing.feature.auth.dto.AuthDto;
import com.chbb.theaketing.feature.auth.dto.AuthFindDto;
import com.chbb.theaketing.feature.auth.dto.SignUpDto;
import com.chbb.theaketing.feature.auth.service.EmailVerifyService;
import com.chbb.theaketing.feature.user.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "인증", description = "회원가입, 로그인, 비밀번호찾기")
@RequiredArgsConstructor
public class AuthController {

    private final EmailVerifyService emailVerifyService;

    @PostMapping("/u/v1/signup")
    @Operation(summary = "회원가입", description = "회원가입")
    public UserDto.UserInfo signup(@RequestBody @Valid SignUpDto.SignUpReq req) throws Exception {
        // TODO 회원가입 로직
        return new UserDto.UserInfo();
    }

    @PostMapping("/u/v1/login")
    @Operation(summary = "로그인", description = "로그인")
    public UserDto.UserInfo login(@RequestBody @Valid AuthDto.LoginReq req) throws Exception {
        // TODO 로그인 로직
        return new UserDto.UserInfo();
    }

    @PostMapping("/u/v1/auth")
    @Operation(summary = "이메일 인증 요청", description = "이메일 인증 요청")
    public void emailAuth(@RequestBody @Valid AuthDto.EmailAuthReq req) throws Exception {
        emailVerifyService.send(req.getEmail());
        return;
    }

    @PostMapping("/u/v1/auth/check")
    @Operation(summary = "이메일 인증 확인", description = "이메일 인증 확인")
    public void emailAuthCheck(@RequestBody @Valid AuthDto.EmailAuthCheckReq req) throws Exception {
        emailVerifyService.check(req);
        return;
    }

    @PostMapping("/u/v1/auth/find")
    @Operation(summary = "비밀번호 찾기", description = "비밀번호 찾기")
    public void find(@RequestBody @Valid AuthFindDto.AuthFindReq req) throws Exception {
        // TODO 비밀번호 재설정 링크 발송 로직
        return;
    }

    @PutMapping("/u/v1/auth")
    @Operation(summary = "비밀번호 재설정", description = "비밀번호 재설정")
    public void changePassword(@RequestBody @Valid AuthFindDto.PasswordChangeReq req) throws Exception {
        // TODO 비밀번호 재설정 로직
        return;
    }

}
