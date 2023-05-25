package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.mapper.BranchRowMapper;
import org.unibuc.persistance.model.Branch;
import org.unibuc.persistance.model.Profile;
import org.unibuc.persistance.repository.BranchRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public class BranchRepositoryImpl implements BranchRepository {

    private final BranchRowMapper rowMapper = new BranchRowMapper();
    @Override
    public void add(Branch branch) {
        actionService.saveAction(this.getClass() + "." + "add");
        String query = "INSERT INTO branch (address_id, code)\n" +
                "VALUES (?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, branch.getAddressId());
            statement.setLong(2, branch.getCode());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Branch was inserted successfully!");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Branch> findById(Long id) {
        actionService.saveAction(this.getClass() + ".findById");
        String query = "SELECT * FROM branch WHERE id = ?";
        Branch branch = null;
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Branch found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No branch found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Branch> findBy(String s) {
        return null;
    }

    @Override
    public List<Branch> findAll() {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "SELECT * FROM branch";
        List<Branch> branches = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                branches.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return branches;
    }

    @Override
    public void delete(Long id) {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "DELETE FROM branch WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Branch with " + id + " was deleted successfully!");
            } else {
                logger.error("No branch was found with " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Branch branch) {
        actionService.saveAction(this.getClass() + ".update");
        String query = "UPDATE branch SET code = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, branch.getCode());
            statement.setLong(2, branch.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Branch with " + branch.getId() + " has successfully updated!");
            } else {
                logger.error("No branch was found for " + branch.getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Long> findByCode(Long code) {
        actionService.saveAction(this.getClass() + ".findByCode");
        String query = "SELECT id FROM branch WHERE code = ?";
        Branch branch = null;
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, code);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Branch found");
                return Optional.of(resultSet.getLong("id"));
            } else {
                logger.error("No branch found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
