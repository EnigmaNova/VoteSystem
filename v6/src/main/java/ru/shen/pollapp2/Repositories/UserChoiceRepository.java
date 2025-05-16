package ru.shen.pollapp2.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.shen.pollapp2.Models.Container;
import ru.shen.pollapp2.Models.User;
import ru.shen.pollapp2.Models.UserChoice;

public interface UserChoiceRepository extends JpaRepository<UserChoice, Long>{
    Optional<UserChoice> findByUserAndContainer(User user, Container container);
    boolean existsByUserIdAndContainerId(Long userId, Long containerId);
}
