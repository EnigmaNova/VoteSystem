package ru.shen.pollapp2.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.shen.pollapp2.Models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByLogin(String login);
}
