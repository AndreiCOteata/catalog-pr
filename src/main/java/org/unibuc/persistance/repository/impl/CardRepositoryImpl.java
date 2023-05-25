package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.converter.CardConverter;
import org.unibuc.persistance.mapper.CardRowMapper;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.model.Card;
import org.unibuc.persistance.repository.CardRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepositoryImpl implements CardRepository {
    private final CardRowMapper rowMapper = new CardRowMapper();
    @Override
    public void add(Card card) {
        actionService.saveAction(this.getClass() + "." + "add");
        String query = "INSERT INTO card (number, expiry_date, cvv, account_id, status)\n" + "VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, card.getNumber());
            statement.setDate(2, card.getExpiryDate());
            statement.setInt(3, card.getCvv());
            statement.setLong(4, card.getAccountId());
            statement.setString(5, card.getStatus());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Card was added to user with id " + card.getAccountId() + "!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Card> findById(Long id) {
        actionService.saveAction(this.getClass() + ".findById");
        String query = "SELECT * FROM card WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Card found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No card found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Card> findBy(String s) {
        return Optional.empty();
    }

    @Override
    public List<Card> findAll() {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "SELECT * FROM card";
        List<Card> cards = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cards.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cards;
    }

    @Override
    public void delete(Long id) {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "DELETE FROM card WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Card with " + id + " was deleted successfully!");
            } else {
                logger.error("No card was found with id" + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Card card) {
        actionService.saveAction(this.getClass() + ".update");
        String query = "UPDATE card SET status = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, card.getStatus());
            statement.setLong(2, card.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Card with " + card.getId() + " was updated!");
            } else {
                logger.error("No card was found with id " + card.getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
