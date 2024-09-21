package com.chbb.theaketing.feature.user.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chbb.theaketing.feature.common.exception.ErrorCode;
import com.chbb.theaketing.feature.common.exception.TheaketingException;
import com.chbb.theaketing.feature.user.entity.User;
import com.chbb.theaketing.feature.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserMapper userMapper;

    @NonNull
    public User findByEmail(String email) throws Exception {
        if (email == null) {
            throw new TheaketingException(ErrorCode.NOT_NULL_PARAMETER);
        }
        User user = userMapper.findByEmail(email);
        if (user == null) {
            throw new TheaketingException(ErrorCode.DATA_NOT_FOUND);
        }
        return user;
    }

    public boolean existByEmail(String email) throws Exception {
        return userMapper.existByEmail(email);
    }
}
