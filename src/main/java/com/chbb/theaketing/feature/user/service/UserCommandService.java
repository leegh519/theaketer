package com.chbb.theaketing.feature.user.service;

import org.springframework.stereotype.Service;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.user.entity.User;
import com.chbb.theaketing.feature.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserMapper userMapper;

    public void insert(User user) throws Exception {
        if (user == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        userMapper.insert(user);
    }

}
