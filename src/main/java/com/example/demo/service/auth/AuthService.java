package com.example.demo.service.auth;

import com.example.demo.dto.SingUpDTO;
import com.example.demo.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SingUpDTO singUpDTO);
}
