package ru.shen.pollapp2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.shen.pollapp2.Models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByLogin(String login);
}
