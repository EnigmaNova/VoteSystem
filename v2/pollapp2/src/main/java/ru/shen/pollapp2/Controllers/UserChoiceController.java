package ru.shen.pollapp2.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.shen.pollapp2.DTO.UserChoiceRequest;
import ru.shen.pollapp2.Models.Answer;
import ru.shen.pollapp2.Models.Container;
import ru.shen.pollapp2.Models.User;
import ru.shen.pollapp2.Repositories.AnswerRepository;
import ru.shen.pollapp2.Repositories.ContainerRepository;
import ru.shen.pollapp2.Repositories.UserRepository;
import ru.shen.pollapp2.Services.UserChoiceService;

@RestController
@RequestMapping("/choices")
public class UserChoiceController {

    private final UserChoiceService userChoiceService;
    private final UserRepository userRepository;
    private final ContainerRepository containerRepository;
    private final AnswerRepository answerRepository;

    public UserChoiceController(UserChoiceService userChoiceService, UserRepository userRepository, ContainerRepository containerRepository, AnswerRepository answerRepository){
        this.userChoiceService = userChoiceService;
        this.userRepository = userRepository;
        this.containerRepository = containerRepository;
        this.answerRepository = answerRepository;
    }

    @PostMapping("/select")
    public ResponseEntity<String> selectAnswer(@RequestBody UserChoiceRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        Container container = containerRepository.findById(request.getContainerId())
                .orElseThrow(() -> new IllegalArgumentException("Контейнер не найден"));
        Answer answer = answerRepository.findById(request.getAnswerId())
                .orElseThrow(() -> new IllegalArgumentException("Ответ не найден"));

        userChoiceService.makeChoice(user, container, answer);
        return ResponseEntity.ok("Выбор успешно сохранен!");
    }

    /*@PostMapping("/select")
    public ResponseEntity<String> selectAnswer(
            @RequestParam Long userId,
            @RequestParam Long containerId,
            @RequestParam Long answerId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        Container container = containerRepository.findById(containerId).orElseThrow(() -> new IllegalArgumentException("Контейнер не найден"));
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("Ответ не найден"));

        userChoiceService.makeChoice(user, container, answer);
        return ResponseEntity.ok("Выбор успешно сохранен!");
    }*/
}
