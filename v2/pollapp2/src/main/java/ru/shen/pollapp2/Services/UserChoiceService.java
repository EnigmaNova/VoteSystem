package ru.shen.pollapp2.Services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.shen.pollapp2.Models.Answer;
import ru.shen.pollapp2.Models.Container;
import ru.shen.pollapp2.Models.User;
import ru.shen.pollapp2.Models.UserChoice;
import ru.shen.pollapp2.Repositories.AnswerRepository;
import ru.shen.pollapp2.Repositories.UserChoiceRepository;

@Service
public class UserChoiceService {

    private final UserChoiceRepository userChoiceRepository;
    private final AnswerRepository answerRepository;

    public UserChoiceService(UserChoiceRepository userChoiceRepository, AnswerRepository answerRepository){
        this.userChoiceRepository = userChoiceRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public void makeChoice(User user, Container container, Answer answer){
        userChoiceRepository.findByUserAndContainer(user, container).ifPresent(existingChoice ->{
            throw new IllegalArgumentException("the user already choice a answer at this poll");
        });

        UserChoice userChoice = new UserChoice();
        userChoice.setUser(user);
        userChoice.setContainer(container);
        userChoice.setAnswer(answer);
        userChoiceRepository.save(userChoice);

        answer.setCount(answer.getCount() + 1);
        answerRepository.save(answer);
    }
}
