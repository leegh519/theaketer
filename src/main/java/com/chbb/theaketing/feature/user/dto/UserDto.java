package com.chbb.theaketing.feature.user.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "내 정보 수정")
    public static class UserUpdateReq {

        @Email(message = "이메일 형식이 아닙니다")
        @Schema(description = "이메일", nullable = true, example = "ghkstoo@naver.com")
        @Nullable
        protected String email;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,20}$", message = "비밀번호는 영어, 숫자, !@#$%^&*를 하나이상 포함하여 6~20자로 설정해야 합니다.")
        @Schema(description = "비밀번호", nullable = true, example = "qwer1234!")
        @Nullable
        protected String password;

        @NotBlank(message = "이름을 입력해주세요")
        @Schema(description = "이름", nullable = true, example = "아무개")
        @Nullable
        protected String name;

        @Pattern(regexp = "^[0-9]{10,11}$", message = "휴대폰 번호 형식이 아닙니다")
        @Schema(description = "휴대폰 번호", nullable = true, example = "01012345678")
        @Nullable
        protected String phone;
    }

    @Getter
    @Schema(description = "회원 정보")
    @NoArgsConstructor
    public static class UserInfo {
        @Schema(description = "ID", example = "1")
        @NonNull
        private Long id;

        @Schema(description = "이메일", example = "ghkstoo@naver.com")
        @NonNull
        private String email;

        @Schema(description = "이름", example = "아무개")
        @NonNull
        private String name;

        @Schema(description = "휴대폰 번호", example = "01012345678")
        @NonNull
        private String phone;

        @Builder
        public UserInfo(Long id, String email, String name, String phone) {
            this.id = id;
            this.email = email;
            this.name = name;
            this.phone = phone;
        }
    }

}
