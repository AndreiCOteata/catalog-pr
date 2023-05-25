package org.unibuc.persistance.mapper.base;

import org.unibuc.persistance.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DefaultRowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
