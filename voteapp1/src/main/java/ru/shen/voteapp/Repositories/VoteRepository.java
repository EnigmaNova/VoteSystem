package ru.shen.voteapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.shen.voteapp.Models.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

}
