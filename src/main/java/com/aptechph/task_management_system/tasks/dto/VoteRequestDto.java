package com.aptechph.task_management_system.tasks.dto;

public class VoteRequestDto {
    private String selectedOption;

    public VoteRequestDto() {}

    public VoteRequestDto(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
