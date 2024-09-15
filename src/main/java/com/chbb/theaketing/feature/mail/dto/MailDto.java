
package com.chbb.theaketing.feature.mail.dto;

import org.springframework.lang.NonNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailDto {

    @Schema(description = "받는사람 주소")
    @NonNull
    private String address;

    @Schema(description = "메일 내용")
    @NonNull
    private String content;

    @Schema(description = "메일 제목")
    @NonNull
    private String title;

    @Builder
    public MailDto(String address, String content, String title) {
        this.address = address;
        this.content = content;
        this.title = title;
    }
}