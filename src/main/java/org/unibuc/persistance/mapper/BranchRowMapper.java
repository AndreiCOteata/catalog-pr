package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Branch;
import org.unibuc.persistance.model.impl.BranchImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchRowMapper implements DefaultRowMapper<Branch> {
    @Override
    public Branch mapRow(ResultSet rs) throws SQLException {
        return BranchImpl.builder()
                .id(rs.getLong("id"))
                .addressId(rs.getLong("address_id"))
                .code(rs.getLong("code")).build();
    }
}
