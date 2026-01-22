package com.aptechph.task_management_system.tasks.dto;

import java.time.LocalDateTime;

public class PollResponseDto {
    private Long id;
    private String question;
    private String options;
    private LocalDateTime createdDate;
    private boolean isActive;

    public PollResponseDto() {}

    public PollResponseDto(Long id, String question, String options, LocalDateTime createdDate, boolean isActive) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.createdDate = createdDate;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
