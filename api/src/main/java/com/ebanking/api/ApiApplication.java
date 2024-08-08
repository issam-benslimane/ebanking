package com.ebanking.api;

import com.ebanking.api.enums.AccountStatus;
import com.ebanking.api.enums.OperationType;
import com.ebanking.api.models.CurrentAccount;
import com.ebanking.api.models.Customer;
import com.ebanking.api.models.SavingAccount;

import com.ebanking.api.models.AccountOperation;
import com.ebanking.api.repositories.AccountOperationRepository;
import com.ebanking.api.repositories.BankAccountRepository;
import com.ebanking.api.repositories.CustomerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    //@Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository,
                            CustomerRepository customerRepository) {
        return args -> {
            //Add customers
            Stream.of("Issam", "Said", "Mohammed").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");

                customerRepository.save(customer);
            });

            //Create customers accounts
            customerRepository.findAll().forEach(customer -> {
                //Create current account
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setCustomer(customer);
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setOverDraft(9000);
                currentAccount.setStatus(AccountStatus.CREATED);
                bankAccountRepository.save(currentAccount);

                //Create saving account
                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setCustomer(customer);
                savingAccount.setBalance(Math.random() * 4000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setInterestRate(5.2);
                savingAccount.setStatus(AccountStatus.CREATED);
                bankAccountRepository.save(savingAccount);
            });

            //Create accounts operations
            bankAccountRepository.findAll().forEach(account -> {
                //Create 5 operations for each account
                for (int i = 0; i < 5; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setAccount(account);
                    accountOperation.setType(Math.random() > .5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setOperationDate(new Date());

                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }

}





