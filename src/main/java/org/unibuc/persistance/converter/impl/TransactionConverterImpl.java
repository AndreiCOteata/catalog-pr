package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.TransactionConverter;
import org.unibuc.persistance.dto.TransactionDto;
import org.unibuc.persistance.model.Transaction;
import org.unibuc.persistance.model.impl.TransactionImpl;

public class TransactionConverterImpl implements TransactionConverter {
    @Override
    public Transaction convertFromDto(TransactionDto transactionDto) {
        return TransactionImpl.builder()
                .id(transactionDto.getId())
                .sendingAccount(transactionDto.getSendingAccount())
                .receivingAccount(transactionDto.getReceivingAccount())
                .type(transactionDto.getType())
                .ammount(transactionDto.getAmmount())
                .status(transactionDto.getStatus())
                .build();
    }

    @Override
    public TransactionDto convertFromEntity(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .sendingAccount(transaction.getSendingAccount())
                .receivingAccount(transaction.getReceivingAccount())
                .type(transaction.getType())
                .ammount(transaction.getAmmount())
                .status(transaction.getStatus())
                .build();
    }
}
