package org.unibuc.persistance.mapper;

import org.unibuc.persistance.mapper.base.DefaultRowMapper;
import org.unibuc.persistance.model.Card;
import org.unibuc.persistance.model.impl.CardImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardRowMapper implements DefaultRowMapper<Card> {
    @Override
    public Card mapRow(ResultSet rs) throws SQLException {
        return CardImpl.builder()
                .id(rs.getLong("id"))
                .accountId(rs.getLong("account_id"))
                .cvv(rs.getInt("cvv"))
                .number(rs.getLong("number"))
                .status(rs.getString("status"))
                .expiryDate(rs.getDate("expiry_date"))
                .build();
    }
}
