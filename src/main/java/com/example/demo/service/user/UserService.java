package com.example.demo.service.user;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserServiceImpl {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserDtoByNickname(String nickname) {
        return this.userRepository.findUserDtoByNickname(nickname);
    }

    @Override
    public boolean existsUser(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public void save(User user) {
        User userDTOfinal = new User();
        this.userRepository.save(userDTOfinal);
    }
}
