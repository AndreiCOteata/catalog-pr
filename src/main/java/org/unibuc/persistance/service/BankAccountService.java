package org.unibuc.persistance.service;

import org.unibuc.persistance.converter.BankAccountConverter;
import org.unibuc.persistance.converter.impl.AccountConverterImpl;
import org.unibuc.persistance.converter.impl.BankAccountConverterImpl;
import org.unibuc.persistance.model.BankAccount;
import org.unibuc.persistance.model.impl.BankAccountImpl;
import org.unibuc.persistance.repository.BankAccountRepository;
import org.unibuc.persistance.repository.impl.AccountRepositoryImpl;
import org.unibuc.persistance.repository.impl.BankAccountRepositoryImpl;

import java.sql.Date;
import java.time.LocalDateTime;

public class BankAccountService {
    private final BankAccountRepository repository;
    private final BankAccountConverter converter;

    public BankAccountService(){
        this.repository = new BankAccountRepositoryImpl();
        this.converter = new BankAccountConverterImpl();
    }

    public void save(Long accountId, Long employeeId, Long branchId){
        BankAccount account = BankAccountImpl.builder()
                .createdAt(Date.valueOf(String.valueOf(LocalDateTime.now())))
                .ammount(0L)
                .employeeId(employeeId)
                .accountId(accountId)
                .branchId(branchId).build();

        this.repository.add(account);
    }

}
