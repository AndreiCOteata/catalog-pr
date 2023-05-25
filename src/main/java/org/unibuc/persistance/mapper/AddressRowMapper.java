package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Address;
import org.unibuc.persistance.model.impl.AddressImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements DefaultRowMapper<Address> {
    @Override
    public Address mapRow(ResultSet rs) throws SQLException {
        return AddressImpl.builder()
                .number(rs.getLong("number"))
                .city(rs.getString("city"))
                .id(rs.getLong("id"))
                .street(rs.getString("street"))
                .country(rs.getString("country"))
                .build();
    }
}
