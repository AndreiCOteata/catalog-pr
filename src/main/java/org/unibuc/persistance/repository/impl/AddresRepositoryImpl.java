package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.dto.AddressDto;
import org.unibuc.persistance.mapper.AddressRowMapper;
import org.unibuc.persistance.model.Address;
import org.unibuc.persistance.repository.AddressRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AddresRepositoryImpl implements AddressRepository {

    private final AddressRowMapper rowMapper = new AddressRowMapper();

    @Override
    public void add(Address address) {
        String query = "INSERT INTO address (city, street, number, country)\n" +
                "VALUES (?, ?, ?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setLong(3, address.getNumber());
            statement.setString(4, address.getCountry());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Address was inserted successfully!");
                actionService.saveAction(this.getClass() + "." + "add");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            databaseConnection.closeConnection();
        }
    }

    @Override
    public Optional<Address> findById(Long id) {
        String query = "SELECT * FROM address WHERE id = ?";
        Address address = null;
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                actionService.saveAction(this.getClass() + ".findById");
                logger.info("Address found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No address found with the given id");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Address> findAddress(AddressDto addressDto){
        actionService.saveAction(this.getClass() + ".findAddressByDto");
        String query = "SELECT * FROM address WHERE street = ? and city = ? and number = ? and country = ?";
        Address address = null;
        try (Connection connection = databaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, addressDto.getStreet());
            statement.setString(2, addressDto.getCity());
            statement.setLong(3, addressDto.getNumber());
            statement.setString(4, addressDto.getCountry());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Address found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No address found with the given details");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            databaseConnection.closeConnection();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Address> findBy(String s) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM address WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            actionService.saveAction(this.getClass() + ".findAll");
            if (rowsDeleted > 0) {
                logger.info("Address with " + id + " was deleted successfully!");
            } else {
                logger.error("No address was found with " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Address address) {
        actionService.saveAction(this.getClass() + ".update");
        String query = "UPDATE address SET city = ?, street = ?, number = ?, country = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setLong(3, address.getNumber());
            statement.setString(4, address.getCountry());
            statement.setLong(3, address.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Address with " + address.getId() + " has successfully updated!");
            } else {
                logger.error("No address was found for " + address.getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
