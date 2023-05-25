package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.mapper.DepartmentRowMapper;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.model.Department;
import org.unibuc.persistance.repository.DepartmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final DepartmentRowMapper rowMapper = new DepartmentRowMapper();
    @Override
    public void add(Department department) {
        actionService.saveAction(this.getClass() + "." + "add");
        String query = "INSERT INTO department (name, leader)\n" + "VALUES (?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, department.getName());
            statement.setLong(2, department.getLeader());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Department was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Department> findById(Long id) {
        actionService.saveAction(this.getClass() + ".findById");
        String query = "SELECT * FROM account WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Department found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No department found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Department> findBy(String s) {
        return Optional.empty();
    }

    @Override
    public List<Department> findAll() {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "SELECT * FROM department";
        List<Department> departments = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                departments.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return departments;
    }

    @Override
    public void delete(Long id) {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "DELETE FROM department WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Department with " + id + " was deleted successfully!");
            } else {
                logger.error("No department was found with " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Department department) {
        actionService.saveAction(this.getClass() + ".update");
        String query = "UPDATE department SET leader = ?, name = ?  WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, department.getLeader());
            statement.setString(2, department.getName());
            statement.setLong(3, department.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Department with " + department.getId() + " has successfully changed its details!");
            } else {
                logger.error("No department was found with given id ");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
