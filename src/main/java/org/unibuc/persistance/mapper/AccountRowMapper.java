package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.model.impl.AccountImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements DefaultRowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs) throws SQLException {
        return  AccountImpl.builder()
                .id(rs.getLong("id"))
                .password(rs.getString("password"))
                .username(rs.getString("username"))
                .profileId(rs.getLong("profile_id"))
                .build();
    }
}
