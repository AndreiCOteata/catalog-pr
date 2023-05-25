package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.mapper.ServiceRowMapper;
import org.unibuc.persistance.model.Account;
import org.unibuc.persistance.model.Service;
import org.unibuc.persistance.repository.ServiceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceRowMapper rowMapper = new ServiceRowMapper();
    @Override
    public void add(Service service) {
        actionService.saveAction(this.getClass() + "." + "add");
        String query = "INSERT INTO service (account_id, started_at, service_name)\n" + "VALUES (?, ?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, service.getAccountId());
            statement.setDate(2, service.getStartedAt());
            statement.setString(3, service.getServiceName());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Service " + service.getServiceName() + " was added to user " + service.getAccountId() + "!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Service> findById(Long id) {
        actionService.saveAction(this.getClass() + ".findById");
        String query = "SELECT * FROM service WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Service found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No service found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Service> findBy(String s) {
        return Optional.empty();
    }

    @Override
    public List<Service> findAll() {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "SELECT * FROM service";
        List<Service> services = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                services.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return services;
    }

    @Override
    public void delete(Long id) {
        actionService.saveAction(this.getClass() + ".findAll");
        String query = "DELETE FROM service WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Service with " + id + " was deleted successfully!");
            } else {
                logger.error("No service was found with id " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Service service) {

    }
}
