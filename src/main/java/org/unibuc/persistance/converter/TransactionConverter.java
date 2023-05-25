package org.unibuc.persistance.converter;

import org.unibuc.persistance.converter.base.BaseConverter;
import org.unibuc.persistance.dto.TransactionDto;
import org.unibuc.persistance.model.Transaction;
import org.unibuc.persistance.model.impl.TransactionImpl;

public interface TransactionConverter extends BaseConverter<Transaction, TransactionDto> {
}
