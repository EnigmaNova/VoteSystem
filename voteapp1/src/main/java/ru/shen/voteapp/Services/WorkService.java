package ru.shen.voteapp.Services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.shen.voteapp.Models.User;
import ru.shen.voteapp.Models.Vote;
import ru.shen.voteapp.Repositories.UserRepository;
import ru.shen.voteapp.Repositories.VoteRepository;

@Service
public class WorkService {

    private UserRepository userRepository;
    private VoteRepository voteRepository;

    public WorkService(UserRepository userRepository, VoteRepository voteRepository){
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional
    public User doVote(Long userId, Long voteId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("we cannot find this user"));
        if(user.getChoice() != null){
            throw new RuntimeException("user made a choice");
        }

        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("we cannot find this type of vote"));
        vote.setCount(vote.getCount() + 1);
        voteRepository.save(vote);

        user.setChoice(vote);
        return userRepository.save(user);
    }
}
