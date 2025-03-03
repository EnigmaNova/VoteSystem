package ru.shen.pollapp2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.shen.pollapp2.Services.UserService;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String login,
                               @RequestParam String password) {
        userService.saveUser(login, password, "USER");
        return "redirect:/good"; // После успешной регистрации
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin() {
        return "redirect:/good"; // Перенаправление после успешного логина
    }

    @GetMapping("/good")
    public String showSuccessPage() {
        return "good";
    }
}
