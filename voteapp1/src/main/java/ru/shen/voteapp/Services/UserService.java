package ru.shen.voteapp.Services;

import org.springframework.stereotype.Service;

import ru.shen.voteapp.Models.User;
import ru.shen.voteapp.Repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setLogin(userDetails.getLogin());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("we cannot find this user"));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
