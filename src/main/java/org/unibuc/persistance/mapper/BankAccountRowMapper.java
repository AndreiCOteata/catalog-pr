package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.BankAccount;
import org.unibuc.persistance.model.impl.BankAccountImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRowMapper implements DefaultRowMapper<BankAccount> {
    @Override
    public BankAccount mapRow(ResultSet rs) throws SQLException {
        return BankAccountImpl.builder()
                .id(rs.getLong("id"))
                .accountId(rs.getLong("account_id"))
                .branchId(rs.getLong("branch_id"))
                .employeeId(rs.getLong("employee_id"))
                .ammount(rs.getLong("ammount"))
                .createdAt(rs.getDate("created_at"))
                .build();
    }
}
