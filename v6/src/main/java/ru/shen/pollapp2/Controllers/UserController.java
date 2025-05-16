package ru.shen.pollapp2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ru.shen.pollapp2.Models.User;
import ru.shen.pollapp2.Services.UserService;

@Controller

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
                               @RequestParam String password,
                               HttpServletResponse response){
        userService.saveUser(login, password, "USER");
        User user = userService.findUserByLogin(login);
        Cookie cookie = new Cookie(String.valueOf(user.getId()), "user");
        cookie.setMaxAge(120);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginForm(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException{
        String login = username;
        User user = userService.findUserByLogin(login);
        if(user == null){
            return "redirect:/login";
        }

        Cookie[] cookies = request.getCookies();
        if(password.equals(user.getPassword())){
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(String.valueOf(user.getId()))){
                    cookie.setMaxAge(1000);
                    response.addCookie(cookie);
                    return "redirect:/containers/home/" + user.getId();
                    }
                }
            }
        }

        Cookie newCookie = new Cookie(String.valueOf(user.getId()), "user");
        newCookie.setMaxAge(300); 
        newCookie.setPath("/"); 
        response.addCookie(newCookie);

        return "redirect:/containers/home/" + user.getId();
    }

    @GetMapping("/good")
    public String showSuccessPage() {
        return "good";
    }
}
