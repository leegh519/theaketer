package com.chbb.theaketing.feature.auth.dto;

import org.springframework.lang.NonNull;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "이메일 인증 요청")
    public static class EmailReq {

        @Email(message = "이메일 형식이 아닙니다")
        @Schema(description = "이메일", requiredMode = RequiredMode.REQUIRED, example = "ghkstoo@naver.com")
        @NonNull
        protected String email;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "로그인 요청")
    public static class LoginReq {

        @NotBlank(message = "이메일을 입력해주세요")
        @Schema(description = "이메일", requiredMode = RequiredMode.REQUIRED, example = "ghkstoo@naver.com")
        @NonNull
        protected String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Schema(description = "비밀번호", requiredMode = RequiredMode.REQUIRED, example = "qwer1234!")
        @NonNull
        protected String password;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "이메일 인증 확인 요청")
    public static class EmailAuthCheckReq {

        @Email(message = "이메일 형식이 아닙니다")
        @Schema(description = "이메일", requiredMode = RequiredMode.REQUIRED, example = "ghkstoo@naver.com")
        @NonNull
        protected String email;

        @NotBlank(message = "인증번호를 입력해주세요")
        @Schema(description = "인증번호", requiredMode = RequiredMode.REQUIRED, example = "129753")
        @NonNull
        protected String authNumber;
    }
}
