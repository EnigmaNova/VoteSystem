package ru.shen.voteapp.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.shen.voteapp.Models.User;
import ru.shen.voteapp.Models.Vote;
import ru.shen.voteapp.Services.VoteService;
import ru.shen.voteapp.Services.WorkService;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final WorkService workService;
    private final VoteService voteService;

    public VoteController(VoteService voteService, WorkService workService){
        this.voteService = voteService;
        this.workService = workService;
    }

    @PutMapping("/user/{userId}/choice/{voteId}")
    public User doChoice(@PathVariable Long userId, @PathVariable Long voteId){
        return workService.doVote(userId, voteId);
    }


    @GetMapping("/vote/{id}")
    public Vote findVoteById(@PathVariable Long id){
        return voteService.getById(id);
    }

    @PostMapping("/newVote")
    public Vote createVote(@RequestBody Vote vote){
        return voteService.createVote(vote);
    }

    @PutMapping("/updateVote/{id}")
    public Vote updateVote(@PathVariable Long id, @RequestBody Vote vote){
        return voteService.updateVote(id, vote);
    }

    @DeleteMapping("/deleteVote/{id}")
    public void deleteVote(@PathVariable Long id){
        voteService.deleteVote(id);
    }
}
