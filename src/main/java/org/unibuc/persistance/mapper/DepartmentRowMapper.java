package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Department;
import org.unibuc.persistance.model.impl.DepartmentImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements DefaultRowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs) throws SQLException {
        return DepartmentImpl.builder()
                .id(rs.getLong("id"))
                .leader(rs.getLong("leader"))
                .name(rs.getString("name"))
                .build();
    }
}
