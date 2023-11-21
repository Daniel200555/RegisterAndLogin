package com.example.demo.service.user;

import com.example.demo.entity.User;

public interface UserServiceImpl {

    User findUserDtoByNickname(String nickname);

    boolean existsUser(String nickname);

    void save(User user);

}
