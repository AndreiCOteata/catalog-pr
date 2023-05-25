package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.mapper.AccountRowMapper;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.repository.AccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {

    private final AccountRowMapper rowMapper = new AccountRowMapper();

    @Override
    public void add(Account account) {
        String query = "INSERT INTO account (username, password, profile_id)\n" + "VALUES (?, ?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setLong(3, account.getProfileId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("User " + account.getUsername() + " was inserted successfully!");
                actionService.saveAction(this.getClass() + "." + "add");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        String query = "SELECT * FROM account WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                actionService.saveAction(this.getClass() + ".findById");
                logger.info("Account found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No account found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> findBy(String username) {
        String query = "SELECT * FROM account WHERE username = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                actionService.saveAction(this.getClass() + ".findByUsername");
                logger.info("Account found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No account found with the given username");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Account> findAll() {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "SELECT * FROM account";
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void delete(Long id) {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "DELETE FROM account WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("User with " + id + " was deleted successfully!");
            } else {
                logger.error("No user was found with " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        actionService.saveAction(this.getClass() + ".update");
        String query = "UPDATE account SET password = ? WHERE username = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, account.getPassword());
            statement.setString(2, account.getUsername());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("User with " + account.getUsername() + " has successfully changed its password!");
            } else {
                logger.error("No user was found with username " + account.getUsername());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
