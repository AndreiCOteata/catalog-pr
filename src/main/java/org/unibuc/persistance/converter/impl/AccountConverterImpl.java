package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.AccountConverter;
import org.unibuc.persistance.dto.AccountDto;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.model.impl.AccountImpl;

public class AccountConverterImpl implements AccountConverter {

    @Override
    public Account convertFromDto(AccountDto accountDto) {
        return AccountImpl.builder()
                .password(accountDto.getPassword())
                .username(accountDto.getUsername())
                .profileId(accountDto.getProfielId())
                .build();
    }

    @Override
    public AccountDto convertFromEntity(Account account) {
        return AccountDto.builder()
                .password(account.getPassword())
                .username(account.getUsername())
                .profielId(account.getProfileId())
                .build();
    }
}
