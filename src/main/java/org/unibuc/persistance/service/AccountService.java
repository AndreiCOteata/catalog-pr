package org.unibuc.persistance.service;

import org.unibuc.exception.DataNotFoundException;
import org.unibuc.persistance.converter.AccountConverter;
import org.unibuc.persistance.converter.impl.AccountConverterImpl;
import org.unibuc.persistance.dto.AccountDto;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.repository.AccountRepository;
import org.unibuc.persistance.repository.impl.AccountRepositoryImpl;

import java.util.zip.DataFormatException;

public class AccountService {
    private final AccountRepository repository;
    private final AccountConverter converter;

    public AccountService(){
        this.repository = new AccountRepositoryImpl();
        this.converter = new AccountConverterImpl();
    }

    public void save(AccountDto dto){
        Account account = converter.convertFromDto(dto);
        repository.add(account);
    }

    public Account findByUsername(String username) throws DataNotFoundException {
        return repository.findBy(username).orElseThrow(() -> new DataNotFoundException("Account not found!"));
    }
}
