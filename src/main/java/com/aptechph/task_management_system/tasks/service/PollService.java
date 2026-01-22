package com.aptechph.task_management_system.tasks.service;

import com.aptechph.task_management_system.tasks.model.Poll;
import com.aptechph.task_management_system.tasks.model.Vote;
import com.aptechph.task_management_system.tasks.repository.PollRepository;
import com.aptechph.task_management_system.tasks.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    public List<Poll> getAllActivePolls() {
        return pollRepository.findByIsActiveTrue();
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public void deletePoll(Long id) {
        pollRepository.deleteById(id);
    }

    public Vote vote(Long pollId, String selectedOption, String ipAddress) {
        if (voteRepository.existsByPollIdAndIpAddress(pollId, ipAddress)) {
            throw new RuntimeException("Already voted");
        }
        Vote vote = new Vote();
        vote.setPollId(pollId);
        vote.setSelectedOption(selectedOption);
        vote.setIpAddress(ipAddress);
        return voteRepository.save(vote);
    }

    public Map<String, Long> getPollResults(Long pollId) {
        List<Vote> votes = voteRepository.findByPollId(pollId);
        return votes.stream()
                .collect(Collectors.groupingBy(Vote::getSelectedOption, Collectors.counting()));
    }

    public Poll getPollById(Long id) {
        return pollRepository.findById(id).orElse(null);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Daily at midnight
    public void archiveOldPolls() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        List<Poll> oldPolls = pollRepository.findAll().stream()
                .filter(poll -> poll.getCreatedDate().isBefore(thirtyDaysAgo))
                .collect(Collectors.toList());
        for (Poll poll : oldPolls) {
            poll.setIsActive(false);
            pollRepository.save(poll);
        }
    }
}
