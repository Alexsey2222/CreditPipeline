package com.example.CreditPipeline.service;

import com.example.CreditPipeline.dto.LoanApplicationRequestDTO;
import com.example.CreditPipeline.dto.LoanOfferDTO;

import java.util.List;

public interface LoanOfferAPIService {

    public List<LoanOfferDTO> generateLoanOffers(LoanApplicationRequestDTO request);

    LoanOfferDTO calculateLoanOffers(LoanApplicationRequestDTO request, boolean insuranceEnabled, boolean salaryClient);
}
