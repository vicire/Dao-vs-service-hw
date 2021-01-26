package core.basesyntax.dao;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Driver;
import core.basesyntax.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class DriverJdbcDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO drivers(name, licenseNumber,login, password) "
                + "VALUES (?,?,?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement createdDriver = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            createdDriver.setString(1, driver.getName());
            createdDriver.setString(2, driver.getLicenseNumber());
            createdDriver.setString(3, driver.getLogin());
            createdDriver.setString(4, driver.getPassword());
            createdDriver.executeUpdate();
            ResultSet resultSet = createdDriver.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getObject("GENERATED_KEY", Long.class));
            }
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t create driver " + driver, e);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String query = "SELECT driver_id, name, licenseNumber, login, password "
                + "FROM drivers WHERE driver_id = ? AND deleted = FALSE";
        Driver driver = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement gettingDriver = connection.prepareStatement(query)) {
            gettingDriver.setLong(1, id);
            ResultSet resultSet = gettingDriver.executeQuery();
            if (resultSet.next()) {
                driver = getDriver(resultSet);
            }
            return Optional.ofNullable(driver);
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get driver by id " + id, e);
        }
    }

    @Override
    public List<Driver> getAll() {
        String query = "SELECT * FROM drivers WHERE deleted = FALSE";
        List<Driver> allDrivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement gettingAll = connection.prepareStatement(query)) {
            ResultSet resultSet = gettingAll.executeQuery();
            while (resultSet.next()) {
                allDrivers.add(getDriver(resultSet));
            }
            return allDrivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get all drivers from database", e);
        }
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers SET name = ?, licenseNumber = ?, login = ?, password = ? "
                + " WHERE driver_id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement updatedDriver = connection.prepareStatement(query)) {
            updatedDriver.setString(1, driver.getName());
            updatedDriver.setString(2, driver.getLicenseNumber());
            updatedDriver.setString(3, driver.getLogin());
            updatedDriver.setString(4, driver.getPassword());
            updatedDriver.setObject(5, driver.getId());
            updatedDriver.executeUpdate();
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t update driver " + driver, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE drivers SET deleted = TRUE WHERE driver_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement deletedDriver = connection.prepareStatement(query)) {
            deletedDriver.setLong(1, id);
            return deletedDriver.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t remove driver by id " + id, e);
        }
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        String query = "SELECT * FROM drivers WHERE login = ? AND deleted = FALSE";
        Driver driver = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                driver = getDriver(resultSet);
            }
            return Optional.ofNullable(driver);
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t find driver by login " + login, e);
        }
    }

    static Driver getDriver(ResultSet result) {
        try {
            Long id = result.getObject("driver_id", Long.class);
            String name = result.getString("name");
            String licenseNumber = result.getString("licenseNumber");
            String login = result.getString("login");
            String password = result.getString("password");
            Driver driver = new Driver(name, licenseNumber, login, password);
            driver.setId(id);
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable make return from database", e);
        }
    }
}
