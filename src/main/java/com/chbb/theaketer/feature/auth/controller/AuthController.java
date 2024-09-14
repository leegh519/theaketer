package com.chbb.theaketer.feature.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chbb.theaketer.feature.auth.dto.AuthDto;
import com.chbb.theaketer.feature.auth.dto.AuthFindDto;
import com.chbb.theaketer.feature.auth.dto.SignUpDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "인증", description = "회원가입, 로그인, 비밀번호찾기")
public class AuthController {

    @PostMapping("/u/v1/signup")
    @Operation(summary = "회원가입", description = "회원가입")
    public SignUpDto.SignUpRes signup(@RequestBody SignUpDto.SignUpReq req) {
        // TODO 회원가입 로직
        return new SignUpDto.SignUpRes();
    }

    @PostMapping("/u/v1/login")
    @Operation(summary = "로그인", description = "로그인")
    public SignUpDto.SignUpRes login(@RequestBody AuthDto.LoginReq req) {
        // TODO 로그인 로직
        return new SignUpDto.SignUpRes();
    }

    @PostMapping("/u/v1/auth")
    @Operation(summary = "이메일 인증 요청", description = "이메일 인증 요청")
    public void emailAuth(@RequestBody AuthDto.EmailAuthReq req) {
        // TODO 이메일 발송 로직
        return;
    }

    @PostMapping("/u/v1/auth/check")
    @Operation(summary = "이메일 인증 확인", description = "이메일 인증 확인")
    public void emailAuthCheck(@RequestBody AuthDto.EmailAuthCheckReq req) {
        // TODO 이메일 인증 확인 로직
        return;
    }

    @PostMapping("/u/v1/auth/find")
    @Operation(summary = "비밀번호 찾기", description = "비밀번호 찾기")
    public void find(@RequestBody AuthFindDto.AuthFindReq req) {
        // TODO 비밀번호 재설정 링크 발송 로직
        return;
    }

    @PutMapping("/u/v1/auth")
    @Operation(summary = "비밀번호 재설정", description = "비밀번호 재설정")
    public void changePassword(@RequestBody AuthFindDto.PasswordChangeReq req) {
        // TODO 비밀번호 재설정 로직
        return;
    }

}
