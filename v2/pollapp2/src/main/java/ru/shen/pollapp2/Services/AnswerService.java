package ru.shen.pollapp2.Services;

import org.springframework.stereotype.Service;

import ru.shen.pollapp2.Models.Answer;
import ru.shen.pollapp2.Repositories.AnswerRepository;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer){
        return answerRepository.save(answer);
    }
}
