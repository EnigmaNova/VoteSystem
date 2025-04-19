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

    public void saveUser(String login, String password, String role) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
    }

    public User findUserByLogin(String login){
        return userRepository.findByLogin(login).orElse(null);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
