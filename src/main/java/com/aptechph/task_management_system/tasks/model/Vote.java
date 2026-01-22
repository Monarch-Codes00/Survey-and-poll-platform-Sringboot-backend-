package com.aptechph.task_management_system.tasks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pollId;
    private String selectedOption;
    private LocalDateTime votedDate;
    private String ipAddress;

    public Vote() {
        this.votedDate = LocalDateTime.now();
    }

    public Vote(Long id, Long pollId, String selectedOption, LocalDateTime votedDate, String ipAddress) {
        this.id = id;
        this.pollId = pollId;
        this.selectedOption = selectedOption;
        this.votedDate = votedDate;
        this.ipAddress = ipAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public LocalDateTime getVotedDate() {
        return votedDate;
    }

    public void setVotedDate(LocalDateTime votedDate) {
        this.votedDate = votedDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
