package com.example.CreditPipeline.dto;

import lombok.Data;

@Data
public class FinishRegistrationRequestDTO {

    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;
    private EmploymentDTO employment;
    private String account;
}
