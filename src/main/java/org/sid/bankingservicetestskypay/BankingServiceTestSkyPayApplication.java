package org.sid.bankingservicetestskypay;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankingServiceTestSkyPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingServiceTestSkyPayApplication.class, args);
    }
    @Bean
    CommandLineRunner start() {
        return args -> {
            AccountService account = new Account();
            account.deposit(1000);
            account.deposit(500);
            account.withdraw(300);
            // Afficher le relevé
            account.printStatement();
        };
    }
}
