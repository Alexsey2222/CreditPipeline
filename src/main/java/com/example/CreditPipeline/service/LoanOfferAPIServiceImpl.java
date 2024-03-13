package com.example.CreditPipeline.service;

import com.example.CreditPipeline.dto.LoanApplicationRequestDTO;
import com.example.CreditPipeline.dto.LoanOfferDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LoanOfferAPIServiceImpl implements LoanOfferAPIService {

    public List<LoanOfferDTO> generateLoanOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {

        List<LoanOfferDTO> offers = new ArrayList<>();

        for (boolean insuranceEnabled : new boolean[]{true, false}) {
            for (boolean salaryClient : new boolean[]{true, false}) {
                if (preScoringPassed(loanApplicationRequestDTO)) {
                    LoanOfferDTO offer = calculateLoanOffers(loanApplicationRequestDTO, insuranceEnabled, salaryClient);
                    offers.add(offer);
                }
            }
        }

        offers.sort(Comparator.comparing(LoanOfferDTO::getRate));

        return offers.subList(0, Math.min(4, offers.size()));
    }

    private boolean preScoringPassed(LoanApplicationRequestDTO loanApplicationRequestDTO) {

        if (isValidName(loanApplicationRequestDTO.getFirstName()) && isValidName(loanApplicationRequestDTO.getLastName()) && isValidMiddleName(loanApplicationRequestDTO.getMiddleName())
                && isValidLoanAmount(loanApplicationRequestDTO.getAmount()) && isValidLoanTerm(loanApplicationRequestDTO.getTerm())
                && isValidDateOfBirth(loanApplicationRequestDTO.getBirthdate()) && isValidPassportSeries(loanApplicationRequestDTO.getPassportSeries()) && isValidPassportNumber(loanApplicationRequestDTO.getPassportNumber())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z]{2,30}");
    }

    private boolean isValidMiddleName(String middleName) {
        if (middleName == null || middleName.isEmpty()) {
            return true;  // Отчество не обязательно, поэтому отсутствие отчества считается валидным
        }
        return middleName.matches("[A-Za-z]{2,30}");
    }

    private boolean isValidLoanAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(new BigDecimal("10000")) >= 0;
    }

    private boolean isValidLoanTerm(int term) {
        return term >= 6;
    }

    private boolean isValidDateOfBirth(LocalDate dob) {
        LocalDate minDob = LocalDate.now().minusYears(18);  // 18 лет с текущего дня
        return dob != null && dob.isBefore(minDob);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("[\\w\\.]{2,50}@[\\w\\.]{2,20}");
    }

    private boolean isValidPassportSeries(String series) {
        return series != null && series.matches("\\d{4}");
    }

    private boolean isValidPassportNumber(String number) {
        return number != null && number.matches("\\d{6}");
    }

    public LoanOfferDTO calculateLoanOffers(LoanApplicationRequestDTO loanApplicationRequestDTO, boolean insuranceEnabled, boolean salaryClient) {

        BigDecimal baseRate = loadBaseRateFromPropertyFile();

        BigDecimal insurancePrice = new BigDecimal("100000");

        BigDecimal rateModifier = insuranceEnabled ? new BigDecimal("-3") : BigDecimal.ZERO;

        if (salaryClient) {
            rateModifier = rateModifier.subtract(new BigDecimal("1"));
        }

        BigDecimal finalRate = baseRate.add(rateModifier);

        LoanOfferDTO loanOffer = new LoanOfferDTO();
        loanOffer.setRate(finalRate);

        return loanOffer;
    }

    private BigDecimal loadBaseRateFromPropertyFile() {
        return new BigDecimal("10");
    }

}
