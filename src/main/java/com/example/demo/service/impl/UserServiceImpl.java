package com.example.demo.service.impl;

import com.example.demo.entity.UserDTO;

public interface UserServiceImpl {

    UserDTO findUserDtoByNickname(String nickname);

    void save(UserDTO userDTO);

}
