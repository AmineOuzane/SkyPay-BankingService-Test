package org.sid.bankingservicetestskypay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Transaction {
    private LocalDate date;
    private int amount;
    private int balanceAfter;
}
