package com.aptechph.task_management_system.tasks.controller;

import com.aptechph.task_management_system.tasks.dto.*;
import com.aptechph.task_management_system.tasks.model.Poll;
import com.aptechph.task_management_system.tasks.model.Vote;
import com.aptechph.task_management_system.tasks.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping
    public ResponseEntity<List<PollResponseDto>> getAllActivePolls() {
        List<Poll> polls = pollService.getAllActivePolls();
        List<PollResponseDto> dtos = polls.stream().map(this::toResponseDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<PollResponseDto> createPoll(@RequestBody PollRequestDto dto) {
        Poll poll = new Poll();
        poll.setQuestion(dto.getQuestion());
        poll.setOptions(dto.getOptions());
        Poll saved = pollService.createPoll(poll);
        return ResponseEntity.ok(toResponseDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<VoteResponseDto> vote(@PathVariable Long id, @RequestBody VoteRequestDto dto, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        Vote vote = pollService.vote(id, dto.getSelectedOption(), ipAddress);
        return ResponseEntity.ok(toVoteResponseDto(vote));
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<Map<String, Long>> getPollResults(@PathVariable Long id) {
        Map<String, Long> results = pollService.getPollResults(id);
        return ResponseEntity.ok(results);
    }

    private PollResponseDto toResponseDto(Poll poll) {
        return new PollResponseDto(poll.getId(), poll.getQuestion(), poll.getOptions(), poll.getCreatedDate(), poll.isActive());
    }

    private VoteResponseDto toVoteResponseDto(Vote vote) {
        return new VoteResponseDto(vote.getId(), vote.getPollId(), vote.getSelectedOption(), vote.getVotedDate(), vote.getIpAddress());
    }
}
