package com.example.CreditPipeline.dto;

import com.example.CreditPipeline.enam.EmploymentStatus;
import lombok.Data;

import javax.swing.text.Position;
import java.math.BigDecimal;

@Data
public class EmploymentDTO {

    private EmploymentStatus employmentStatus;
    private String employerINN;
    private BigDecimal salary;
    private Position position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
}
