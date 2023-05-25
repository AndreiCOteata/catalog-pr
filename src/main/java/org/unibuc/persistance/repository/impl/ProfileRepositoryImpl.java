package org.unibuc.persistance.repository.impl;

import org.unibuc.persistance.mapper.ProfileRowMapper;
import org.unibuc.persistance.model.Profile;
import org.unibuc.persistance.repository.ProfileRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfileRepositoryImpl implements ProfileRepository {
    private final ProfileRowMapper rowMapper = new ProfileRowMapper();

    @Override
    public void add(Profile profile) {
        String query = "INSERT INTO profile (first_name, last_name, cnp, email, address_id, phone)\n" +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, profile.getFirstName());
            statement.setString(2, profile.getLastName());
            statement.setLong(3, profile.getCnp());
            statement.setString(4, profile.getEmail());
            statement.setLong(5, profile.getAddressId());
            statement.setLong(6, profile.getPhone());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Profile for " + profile.getEmail() + " was inserted successfully!");
                actionService.saveAction(this.getClass() + "." + "add");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Profile> findById(Long id) {
        String query = "SELECT * FROM profile WHERE id = ?";
        Profile profile = null;
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                actionService.saveAction(this.getClass() + ".findById");
                logger.info("Profile found");
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
    public Optional<Profile> findBy(String email) {
        String query = "SELECT * FROM profile WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                actionService.saveAction(this.getClass() + ".findByEmail");
                logger.info("Profile found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                logger.error("No profile found with the given email");
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Profile> findAll() {
        String query = "SELECT * FROM profile";
        List<Profile> profiles = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                profiles.add(rowMapper.mapRow(resultSet));
            }
            actionService.saveAction(this.getClass() + ".findAll");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return profiles;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM profile WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Profile with " + id + " was deleted successfully!");
            } else {
                logger.error("No profile was found with " + id);
            }
            actionService.saveAction(this.getClass() + ".findAll");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Profile profile) {
        actionService.saveAction(this.getClass() + ".update");
        String query = "UPDATE profile SET last_name = ?, address_id = ?, phone = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, profile.getLastName());
            statement.setLong(2, profile.getAddressId());
            statement.setLong(3, profile.getPhone());
            statement.setLong(4, profile.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Profile with " + profile.getId() + " has successfully updated!");
            } else {
                logger.error("No profile was found for " + profile.getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
