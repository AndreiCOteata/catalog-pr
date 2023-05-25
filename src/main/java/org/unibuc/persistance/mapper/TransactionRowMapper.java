package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Transaction;
import org.unibuc.persistance.model.impl.TransactionImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements DefaultRowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs) throws SQLException {
        return TransactionImpl.builder()
                .id(rs.getLong("id"))
                .sendingAccount(rs.getLong("sending_account"))
                .receivingAccount(rs.getLong("receiving_account"))
                .ammount(rs.getLong("ammount"))
                .status(rs.getString("status"))
                .type(rs.getString("type"))
                .build();
    }
}
