package com.aptechph.task_management_system.tasks.repository;

import com.aptechph.task_management_system.tasks.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByPollId(Long pollId);
    boolean existsByPollIdAndIpAddress(Long pollId, String ipAddress);
}
