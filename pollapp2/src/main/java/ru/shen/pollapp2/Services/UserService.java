package ru.shen.pollapp2.Services;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.shen.pollapp2.Models.User;
import ru.shen.pollapp2.Repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(String login, String password, String role) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password)); // ХЕШИРУЕМ перед сохранением!
        user.setRole(role);
        userRepository.save(user);
    }

    public User findUserByLogin(String login){
        return userRepository.findByLogin(login).orElse(null);
    }
}
