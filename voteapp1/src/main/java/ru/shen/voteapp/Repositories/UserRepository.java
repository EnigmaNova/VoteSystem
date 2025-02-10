package ru.shen.voteapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.shen.voteapp.Models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
