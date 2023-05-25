package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Employee;
import org.unibuc.persistance.model.impl.EmployeeImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements DefaultRowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs) throws SQLException {
        return EmployeeImpl.builder()
                .id(rs.getLong("id"))
                .departmentId(rs.getLong("department_id"))
                .startedAt(rs.getDate("started_at"))
                .salary(rs.getLong("salary"))
                .profileId(rs.getLong("profile_id"))
                .position(rs.getString("position"))
                .branchId(rs.getLong("branch_id"))
                .build();
    }
}
