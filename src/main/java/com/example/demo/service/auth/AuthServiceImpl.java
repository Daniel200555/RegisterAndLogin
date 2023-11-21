package com.example.demo.service.auth;

import com.example.demo.dto.SingUpDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SingUpDTO singUpDTO) {
        if (userRepository.existsByNickname(singUpDTO.getNickname()))
            return null;
        User user = new User();
        user.setEmail(singUpDTO.getEmail());
        user.setNickname(singUpDTO.getNickname());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setNickname(createdUser.getNickname());
        return userDTO;
    }
}
