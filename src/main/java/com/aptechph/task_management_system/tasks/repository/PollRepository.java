package com.aptechph.task_management_system.tasks.repository;

import com.aptechph.task_management_system.tasks.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByIsActiveTrue();
}
