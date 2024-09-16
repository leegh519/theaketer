package com.chbb.theaketing.feature.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chbb.theaketing.feature.user.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "회원", description = "회원 API")
@Slf4j
public class UserController {
    @PostMapping("/u/v1/user")
    @Operation(summary = "내정보", description = "내정보")
    public UserDto.UserInfo fetch() {
        // TODO 내 정보 조회

        return new UserDto.UserInfo();
    }

    // @PutMapping("/u/v1/user")
    // @Operation(summary = "내 정보 수정", description = "내 정보 수정")
    // public void update(@RequestBody @Valid UserDto.UserUpdateReq req) {
    // return;
    // }

    // @PostMapping("/u/v1/withdraw")
    // @Operation(summary = "탈퇴", description = "탈퇴")
    // public void withdraw() {

    // return;
    // }
}
