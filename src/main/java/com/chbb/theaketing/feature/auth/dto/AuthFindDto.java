package com.chbb.theaketing.feature.auth.dto;

import org.springframework.lang.NonNull;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthFindDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "비밀번호 찾기")
    public static class AuthFindReq {

        @Email(message = "이메일 형식이 아닙니다")
        @Schema(description = "이메일", requiredMode = RequiredMode.REQUIRED, example = "abc@abc.com")
        @NonNull
        protected String email;

        @NotBlank(message = "이름을 입력해주세요")
        @Schema(description = "이름", requiredMode = RequiredMode.REQUIRED, example = "아무개")
        @NonNull
        protected String name;

        @Pattern(regexp = "^[0-9]{10,11}$", message = "휴대폰 번호 형식이 아닙니다")
        @Schema(description = "휴대폰 번호", requiredMode = RequiredMode.REQUIRED, example = "01012345678")
        @NonNull
        protected String phone;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "비밀번호 재설정")
    public static class PasswordChangeReq {

        @NotBlank(message = "인증코드를 입력해주세요")
        @Schema(description = "인증코드", requiredMode = RequiredMode.REQUIRED, example = "qwer1234!")
        @NonNull
        protected String code;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Schema(description = "비밀번호", requiredMode = RequiredMode.REQUIRED, example = "qwer1234!")
        @NonNull
        protected String password;

    }
}
