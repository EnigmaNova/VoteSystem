package ru.shen.pollapp2.Controllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.shen.pollapp2.Models.Answer;
import ru.shen.pollapp2.Models.Container;
import ru.shen.pollapp2.Services.AnswerService;
import ru.shen.pollapp2.Services.ContainerService;

@Controller
@RequestMapping("/containers")
public class ContainerController {

    private final ContainerService containerService;
    private final AnswerService answerService;

    public ContainerController(ContainerService containerService, AnswerService answerService){
        this.containerService = containerService;
        this.answerService = answerService;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("container", new Container());
        return "containerForm";
    }

    // Обработать отправку формы и сохранить контейнер
    @PostMapping("/new")
    public String createContainer(@ModelAttribute Container container) {
        containerService.saveContainer(container);
        return "good";
    }

    @GetMapping("/home")
    public String getContainers(Model model) {
        List<Container> containers = containerService.findAll();
        model.addAttribute("containers", containers);
        return "home"; // имя страницы с Thymeleaf
    }

    @GetMapping("/container/{id}")
public String getContainerDetails(@PathVariable("id") Long containerId, Model model) {
    Container container = containerService.findById(containerId);

        if (container != null) {
            // Получаем все ответы, связанные с данным контейнером
            List<Answer> answers = answerService.findByContainerId(containerId);
            model.addAttribute("container", container);
            model.addAttribute("answers", answers);
        } else {
            model.addAttribute("error", "Container not found");
        }

        return "container-details"; // Имя шаблона для отображения
}

}
