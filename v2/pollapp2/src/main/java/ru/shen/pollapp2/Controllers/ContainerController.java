package ru.shen.pollapp2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.shen.pollapp2.Models.Container;
import ru.shen.pollapp2.Services.ContainerService;

@Controller
@RequestMapping("/containers")
public class ContainerController {

    private final ContainerService containerService;
    public ContainerController(ContainerService containerService){
        this.containerService = containerService;
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

}
