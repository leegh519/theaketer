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

public class SignUpDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "회원가입")
    public static class SignUpReq {

        @Email(message = "이메일 형식이 아닙니다")
        @Schema(description = "이메일", requiredMode = RequiredMode.REQUIRED, example = "ghkstoo@naver.com")
        @NonNull
        protected String email;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,20}$", message = "비밀번호는 영어, 숫자, !@#$%^&*를 하나이상 포함하여 6~20자로 설정해야 합니다.")
        @Schema(description = "비밀번호", requiredMode = RequiredMode.REQUIRED, example = "qwer1234!")
        @NonNull
        protected String password;

        @NotBlank(message = "이름을 입력해주세요")
        @Schema(description = "이름", requiredMode = RequiredMode.REQUIRED, example = "아무개")
        @NonNull
        protected String name;

        @Pattern(regexp = "^[0-9]{10,11}$", message = "휴대폰 번호 형식이 아닙니다")
        @Schema(description = "휴대폰 번호", requiredMode = RequiredMode.REQUIRED, example = "01012345678")
        @NonNull
        protected String phone;

    }

}
