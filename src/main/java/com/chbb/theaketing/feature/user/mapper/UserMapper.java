package com.chbb.theaketing.feature.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chbb.theaketing.feature.user.entity.User;

@Mapper
public interface UserMapper {

    public void insert(User user) throws Exception;

    public User findByEmail(String email) throws Exception;

}
