package ru.shen.voteapp.Services;

import org.springframework.stereotype.Service;

import ru.shen.voteapp.Models.Vote;
import ru.shen.voteapp.Repositories.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    public VoteService(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    public Vote getById(Long id){
        return voteRepository.findById(id).orElseThrow(() -> new RuntimeException("we cannot find this vote"));
    }
    public Vote createVote(Vote vote){
        return voteRepository.save(vote);
    }

    public Vote updateVote(Long id, Vote voteDetails){
        return voteRepository.findById(id).map(vote ->{
            vote.setTitle(voteDetails.getTitle());
            return voteRepository.save(vote);
        }).orElseThrow(()-> new RuntimeException("we cannot find this posotion of vote"));
    }

    public void deleteVote(Long id){
        voteRepository.deleteById(id);
    }
}
