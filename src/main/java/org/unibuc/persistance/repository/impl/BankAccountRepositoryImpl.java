package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.mapper.BankAccountRowMapper;
import org.unibuc.persistance.model.BankAccount;
import org.unibuc.persistance.repository.BankAccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final BankAccountRowMapper rowMapper = new BankAccountRowMapper();
    @Override
    public void add(BankAccount bankAccount) {
        actionService.saveAction(this.getClass() + "." + "add");
        String query = "INSERT INTO bank_account (ammount, account_id, created_at, branch_id, employee_id)\n" + "VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, bankAccount.getAmmount());
            statement.setLong(2, bankAccount.getAccountId());
            statement.setDate(3, bankAccount.getCreatedAt());
            statement.setLong(4, bankAccount.getBranchId());
            statement.setLong(5, bankAccount.getEmployeeId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("BankAccount was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        actionService.saveAction(this.getClass() + ".findById");
        String query = "SELECT * FROM bank_account WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("BankAccount found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No bank account found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<BankAccount> findBy(String s) {
        return Optional.empty();
    }

    @Override
    public List<BankAccount> findAll() {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "SELECT * FROM bank_account";
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bankAccounts.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bankAccounts;
    }

    @Override
    public void delete(Long id) {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "DELETE FROM bank_account WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Bank account with " + id + " was deleted successfully!");
            } else {
                logger.error("No bank acocunt was found with id" + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(BankAccount bankAccount) {
        actionService.saveAction(this.getClass() + ".update");
        String query = "UPDATE bank_account SET ammount = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, bankAccount.getAmmount());
            statement.setLong(2, bankAccount.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Bank account with " + bankAccount.getId() + " was updated!");
            } else {
                logger.error("No bank account was found with id " + bankAccount.getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
