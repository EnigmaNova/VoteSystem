package ru.shen.pollapp2.Components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.shen.pollapp2.Services.UserService;

@Component
public class DataLoader implements CommandLineRunner{

    private final UserService userService;

    public DataLoader(UserService userService){
        this.userService = userService;
    }

    @Override
    public void run(String ... args) throws Exception{
        if (userService.findUserByLogin("misha") == null) { // Проверяем, есть ли уже такой пользователь
            userService.saveUser("misha", "misha", "USER");
        }
    }

}
