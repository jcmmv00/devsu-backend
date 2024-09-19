package com.devsu.devsu.application.commands;

import java.time.LocalDate;

public class ReportGenerationDTO {
    private String accountNumber;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReportGenerationDTO(String accountNumber, LocalDate startDate, LocalDate endDate) {
        this.accountNumber = accountNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}