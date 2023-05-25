package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Service;
import org.unibuc.persistance.model.impl.ServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRowMapper implements DefaultRowMapper<Service> {
    @Override
    public Service mapRow(ResultSet rs) throws SQLException {
        return ServiceImpl.builder()
                .id(rs.getLong("id"))
                .accountId(rs.getLong("account_id"))
                .serviceName(rs.getString("service_name"))
                .startedAt(rs.getDate("started_at"))
                .build();
    }
}
