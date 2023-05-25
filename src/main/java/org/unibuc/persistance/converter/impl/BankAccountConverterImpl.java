package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.BankAccountConverter;
import org.unibuc.persistance.dto.BankAccountDto;
import org.unibuc.persistance.model.BankAccount;
import org.unibuc.persistance.model.impl.BankAccountImpl;

public class BankAccountConverterImpl implements BankAccountConverter {
    @Override
    public BankAccount convertFromDto(BankAccountDto bankAccountDto) {
        return BankAccountImpl.builder()
                .id(bankAccountDto.getId())
                .accountId(bankAccountDto.getAccountId())
                .ammount(bankAccountDto.getAmmount())
                .branchId(bankAccountDto.getBranchId())
                .employeeId(bankAccountDto.getEmployeeId())
                .createdAt(bankAccountDto.getCreatedAt())
                .build();
    }

    @Override
    public BankAccountDto convertFromEntity(BankAccount bankAccount) {
        return BankAccountDto.builder()
                .id(bankAccount.getId())
                .accountId(bankAccount.getAccountId())
                .employeeId(bankAccount.getEmployeeId())
                .branchId(bankAccount.getBranchId())
                .ammount(bankAccount.getAmmount())
                .createdAt(bankAccount.getCreatedAt())
                .build();
    }
}
