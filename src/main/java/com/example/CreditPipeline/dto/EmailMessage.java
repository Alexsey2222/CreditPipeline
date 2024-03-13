package com.example.CreditPipeline.dto;

import com.example.CreditPipeline.enam.Theme;
import lombok.Data;

@Data
public class EmailMessage {

    private String address;
    private Theme theme;
    private Long applicationId;
}
