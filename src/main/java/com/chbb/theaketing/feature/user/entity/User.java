package com.chbb.theaketing.feature.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    protected String email;

    protected String password;

    protected String name;

    protected String phone;

    protected LocalDateTime joinDate;

    protected LocalDateTime withdrawalDate;

    @Builder
    public User(Long id, String email, String password, String name, String phone, LocalDateTime joinDate,
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
