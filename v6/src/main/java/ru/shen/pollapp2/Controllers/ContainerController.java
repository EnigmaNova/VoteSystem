package ru.shen.pollapp2.Controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import ru.shen.pollapp2.Models.Answer;
import ru.shen.pollapp2.Models.Container;
import ru.shen.pollapp2.Models.UserChoice;
import ru.shen.pollapp2.Services.AnswerService;
import ru.shen.pollapp2.Services.ContainerService;
import ru.shen.pollapp2.Services.UserChoiceService;
import ru.shen.pollapp2.Services.UserService;

@Controller
@RequestMapping("/containers")
public class ContainerController {

    private final UserService userService;

    private final ContainerService containerService;
    private final AnswerService answerService;
    private final UserChoiceService userChoiceService;

    public ContainerController(ContainerService containerService, AnswerService answerService, UserChoiceService userChoiceService, UserService userService){
        this.containerService = containerService;
        this.answerService = answerService;
        this.userChoiceService = userChoiceService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("container", new Container());
        return "containerForm";
    }

    @PostMapping("/new")
    public String createContainer(@ModelAttribute Container container) {
        containerService.saveContainer(container);
        return "good";
    }

    @GetMapping("/home/{userId}")
    public String getContainers(@PathVariable Long userId, Model model) {
        List<Container> containers = containerService.findAll();
        model.addAttribute("containers", containers);
        model.addAttribute("userId", userId);
        return "home"; // имя страницы с Thymeleaf
        
    }

    
    @GetMapping("/{userId}/container/{containerId}")
    public String getContainerDetails(
                                        @PathVariable("userId") Long userId,
                                        @PathVariable("containerId") Long containerId,
                                         Model model) {
        Container container = containerService.findById(containerId);
        if (container == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Container not found");
        }

        List<Answer> answers = answerService.findByContainerId(containerId);
        if (answers == null) {
            answers = new ArrayList<>();
        }

        int maxCount = answers.stream()
                          .mapToInt(Answer::getCount)
                          .max()
                          .orElse(1);

        model.addAttribute("container", container);
        model.addAttribute("user", userId);
        model.addAttribute("answers", answers);
        model.addAttribute("maxCount", maxCount);

        if(userChoiceService.existsByUserAndContainerId(userId, containerId)){
            model.addAttribute("containerId", containerId);
            return "pool";
        }

        return "container-details";
    }

    @PostMapping("/{userId}/container/{containerId}")
    public String submitContDet(@PathVariable Long userId,
                                 @PathVariable Long containerId, 
                                @RequestParam("choice") Long answerId){
        
        UserChoice userChoice = new UserChoice();
        userChoice.setUser(userService.findUserById(userId));
        userChoice.setContainer(containerService.findById(containerId));
        userChoice.setAnswer(answerService.findById(answerId));

        userChoiceService.createUserChoice(userChoice);
        Answer answer = answerService.findById(answerId);
        answer.setCount(answer.getCount()+1);
        return "redirect:/containers/home/" + userId;
    }
}
