package org.sid.bankingservicetestskypay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class Account implements AccountService{
    private final List<Transaction> transactions;
    private int balance = 0;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Account() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {

        log.info("Depositing amount: {}", amount);

        // Vérifier si le montant est positif
        if (amount <= 0) {
            log.error("Deposit amount must be positive");
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        // Mettre à jour le solde
        balance += amount;

        // Créer une nouvelle transaction
        Transaction transaction = Transaction.builder()
                .date(LocalDate.now())
                .balanceAfter(balance)
                .amount(amount)
                .build();

        // Ajouter la transaction à la liste des transactions
        transactions.add(transaction);

        log.info("Deposited {}. New balance is {}", amount, transaction.getBalanceAfter());
    }

    @Override
    public void withdraw(int amount) {

        log.info("Withdrawing amount: {}", amount);

        // Vérifier si le montant est positif
        if (amount <= 0) {
            log.error("Withdrawal amount must be positive");
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        // Vérifier si le solde est suffisant
        if (amount > balance) {
            log.error("Insufficient funds for withdrawal");
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        // Mettre à jour le solde
        balance -= amount;

        // Créer une nouvelle transaction
        Transaction transaction = Transaction.builder()
                .date(LocalDate.now())
                .balanceAfter(balance)
                .amount(-amount)
                .build();

        // Ajouter la transaction à la liste des transactions
        transactions.add(transaction);

        log.info("Withdrew {}. New balance is {}", amount, transaction.getBalanceAfter());
    }

    @Override
    public void printStatement() {

        System.out.println("DATE || AMOUNT || BALANCE");
        List<Transaction> copy = new ArrayList<>(transactions);
        // Reverse the list for chronological order like shown in the example of the test
        Collections.reverse(copy);
        for (Transaction t : copy) {
            String amountStr = (t.getAmount() >= 0 ? "+" : "") + t.getAmount();
            System.out.printf("%s | %s | %d%n",
                    t.getDate().format(FORMATTER),
                    amountStr,
                    t.getBalanceAfter()
            );
        }
        log.info("Statement printed successfully");
    }
}