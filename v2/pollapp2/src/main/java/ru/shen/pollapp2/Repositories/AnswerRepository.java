package ru.shen.pollapp2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.shen.pollapp2.Models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
