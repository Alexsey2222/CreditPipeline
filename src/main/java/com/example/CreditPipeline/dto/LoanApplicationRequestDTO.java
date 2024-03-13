package com.example.CreditPipeline.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanApplicationRequestDTO {

    private BigDecimal amount;
    private Integer term;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthdate;
    private String passportSeries;
    private String passportNumber;

}
