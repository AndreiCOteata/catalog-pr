package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.mapper.EmployeeRowMapper;
import org.unibuc.persistance.model.Employee;
import org.unibuc.persistance.repository.EmployeeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeRowMapper rowMapper = new EmployeeRowMapper();
    @Override
    public void add(Employee employee) {
        actionService.saveAction(this.getClass() + "." + "add");
        String query = "INSERT INTO employee (profile_id, position, department_id, started_at, salary, branch_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, employee.getProfileId());
            statement.setString(2, employee.getPosition());
            statement.setLong(3, employee.getDepartmentId());
            statement.setDate(4, employee.getStartedAt());
            statement.setLong(5, employee.getSalary());
            statement.setLong(6, employee.getBranchId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("New employee was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        actionService.saveAction(this.getClass() + ".findById");
        String query = "SELECT * FROM employee WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Employee found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No employee found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean isEmployee(Long profileId){
        actionService.saveAction(this.getClass() + ".isEmployee");
        String query = "SELECT 1 FROM employee WHERE profile_id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, profileId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Employee found");
                return true;
            } else {
                logger.error("No employee found with the given profile id");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Employee> findBy(String profileId) {
        actionService.saveAction(this.getClass() + ".findByEmail");
        String query = "SELECT * FROM employee WHERE profile_id = ?";
        Employee employee = null;
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, Long.parseLong(profileId));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Employee found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No employee found with the profile id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    @Override
    public void delete(Long id) {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "DELETE FROM employee WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Employee with " + id + " was deleted successfully!");
            } else {
                logger.error("No employee was found with " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(Employee employee) {
    }
}
