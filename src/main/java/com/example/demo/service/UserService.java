package com.example.demo.service;

import com.example.demo.entity.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserServiceImpl {

    private UserRepository userRepository;


    @Override
    public UserDTO findUserDtoByNickname(String nickname) {
        return this.userRepository.findUserDtoByNickname(nickname);
    }

    @Override
    public void save(UserDTO userDTO) {

    }
}
