package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Profile;
import org.unibuc.persistance.model.impl.ProfileImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRowMapper implements DefaultRowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet rs) throws SQLException {
        return ProfileImpl.builder()
                .addressId(rs.getLong("address_id"))
                .id(rs.getLong("id"))
                .phone(rs.getLong("phone"))
                .cnp(rs.getLong("cnp"))
                .email(rs.getString("email"))
                .lastName(rs.getString("last_name"))
                .firstName(rs.getString("first_name"))
                .build();
    }
}
