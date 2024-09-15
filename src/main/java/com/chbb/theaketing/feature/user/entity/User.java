package com.chbb.theaketing.feature.user.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    protected Integer id;

    protected String email;

    protected String password;

    protected String name;

    protected String phone;

    protected LocalDateTime joinDate;

    protected LocalDateTime withdrawalDate;

    @Builder
    public User(Integer id, String email, String password, String name, String phone, LocalDateTime joinDate,
            LocalDateTime withdrawalDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.joinDate = joinDate;
        this.withdrawalDate = withdrawalDate;
    }

}
