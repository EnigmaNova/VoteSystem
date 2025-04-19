package ru.shen.pollapp2.Services;

import java.util.List;

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

    public List<Answer> findByContainerId(Long id){
        return answerRepository.findByContainerId(id);
    }

    public Answer findById(Long id){
        return answerRepository.findById(id).orElse(null);
    }
}
