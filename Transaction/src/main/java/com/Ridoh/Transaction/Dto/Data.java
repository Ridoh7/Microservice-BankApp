package com.Ridoh.Transaction.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Data {
    public String accountName;
    public String accountNumber;
    public BigDecimal accountBalance;

}
