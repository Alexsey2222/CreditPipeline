package com.example.CreditPipeline.controller;

import com.example.CreditPipeline.dto.CreditDTO;
import com.example.CreditPipeline.dto.ScoringDataDTO;
import com.example.CreditPipeline.service.LoanOfferAPIServiceImpl;
import com.example.CreditPipeline.dto.LoanApplicationRequestDTO;
import com.example.CreditPipeline.dto.LoanOfferDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/conveyor")
@AllArgsConstructor
public class Controller {

    private final LoanOfferAPIServiceImpl loanOfferAPIService;

    @PostMapping("/conveyor/offers")
    public List<LoanOfferDTO> calculateLoanOffers(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return loanOfferAPIService.generateLoanOffers(loanApplicationRequestDTO);
    }

//    @PutMapping("/conveyor/calculation")
//    public CreditDTO filterEmployees(@RequestBody ScoringDataDTO scoringDa1taDTO) {
//        return loanOfferAPIService.(filterDTO, pageable);
//    }

}
