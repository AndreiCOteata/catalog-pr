package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.mapper.TransactionRowMapper;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.model.Transaction;
import org.unibuc.persistance.repository.TransactionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionRowMapper rowMapper = new TransactionRowMapper();

    @Override
    public void add(Transaction transaction) {
        actionService.saveAction(this.getClass() + "." + "add");
        String query = "INSERT INTO transaction (sending_account, receiving_account, ammount, status, type)\n" + "VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, transaction.getSendingAccount());
            statement.setLong(2, transaction.getReceivingAccount());
            statement.setLong(3, transaction.getAmmount());
            statement.setString(4, transaction.getStatus());
            statement.setString(5, transaction.getType());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Transaction was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        actionService.saveAction(this.getClass() + ".findById");
        String query = "SELECT * FROM transaction WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Transaction found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No transaction found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> findBy(String s) {
        return Optional.empty();
    }

    @Override
    public List<Transaction> findAll() {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "SELECT * FROM transaction";
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactions.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void delete(Long id) {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "DELETE FROM transaction WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Transaction with " + id + " was deleted successfully!");
            } else {
                logger.error("No transaction was found with id " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Transaction transaction) {
        actionService.saveAction(this.getClass() + ".update");
        String query = "UPDATE transaction SET status = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, transaction.getStatus());
            statement.setLong(2, transaction.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Transaction with id " + transaction.getId() + " has been updated!");
            } else {
                logger.error("No transaction was found with id " + transaction.getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
