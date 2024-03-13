package com.example.CreditPipeline.dto;

import com.example.CreditPipeline.enam.ChangeType;
import com.example.CreditPipeline.enam.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationStatusHistoryDTO {

    private Status status;
    private LocalDateTime time;
    private ChangeType changeType;
}
