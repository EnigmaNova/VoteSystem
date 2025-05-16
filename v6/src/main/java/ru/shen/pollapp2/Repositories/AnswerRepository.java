package ru.shen.pollapp2.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.shen.pollapp2.Models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
    List<Answer> findByContainerId(Long containerId);
    Optional<Answer> findById(Long id);
}
