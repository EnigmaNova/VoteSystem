package ru.shen.pollapp2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.shen.pollapp2.Models.Answer;
import ru.shen.pollapp2.Models.Container;
import ru.shen.pollapp2.Services.AnswerService;
import ru.shen.pollapp2.Services.ContainerService;

@Controller
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final ContainerService containerService;

    public AnswerController(AnswerService answerService, ContainerService containerService) {
        this.answerService = answerService;
        this.containerService = containerService;
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("answer", new Answer());
        model.addAttribute("containers", containerService.findAll()); 
        return "answerForm";
    }

    @PostMapping("/new")
    public String createAnswer(@ModelAttribute Answer answer, @RequestParam("containerId") Long containerId) {
        Container container = containerService.findById(containerId);
        answer.setContainer(container); 
        answerService.createAnswer(answer); 
        return "good";
    }
}
