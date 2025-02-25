package ru.shen.pollapp2.Services;

import org.springframework.stereotype.Service;

import ru.shen.pollapp2.Models.User;
import ru.shen.pollapp2.Repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

}
