package com.aptechph.task_management_system.tasks.dto;

import java.time.LocalDateTime;

public class VoteResponseDto {
    private Long id;
    private Long pollId;
    private String selectedOption;
    private LocalDateTime votedDate;
    private String ipAddress;

    public VoteResponseDto() {}

    public VoteResponseDto(Long id, Long pollId, String selectedOption, LocalDateTime votedDate, String ipAddress) {
        this.id = id;
        this.pollId = pollId;
        this.selectedOption = selectedOption;
        this.votedDate = votedDate;
        this.ipAddress = ipAddress;
    }

    // Getters and setters
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
