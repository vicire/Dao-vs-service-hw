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
        String query = "INSERT INTO drivers(name, licenseNumber) VALUES (?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement createdDriver = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            createdDriver.setString(1, driver.getName());
            createdDriver.setString(2, driver.getLicenseNumber());
            createdDriver.executeUpdate();
            ResultSet result = createdDriver.getGeneratedKeys();
            if (result.next()) {
                driver.setId(result.getObject(1, Long.class));
            }
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t create driver " + driver, e);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String query = "SELECT driver_id, name, licenseNumber "
                + "FROM drivers WHERE driver_id = ? AND deleted = FALSE";
        Driver driver = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement gettingDriver = connection.prepareStatement(query)) {
            gettingDriver.setLong(1, id);
            ResultSet result = gettingDriver.executeQuery();
            if (result.next()) {
                driver = getDriver(result);
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
            ResultSet result = gettingAll.executeQuery();
            while (result.next()) {
                allDrivers.add(getDriver(result));
            }
            return allDrivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get all drivers from database", e);
        }
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers SET name = ?, licenseNumber = ? "
                + " WHERE driver_id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement updatedDriver = connection.prepareStatement(query)) {
            updatedDriver.setString(1, driver.getName());
            updatedDriver.setString(2, driver.getLicenseNumber());
            updatedDriver.setObject(3, driver.getId());
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

    static Driver getDriver(ResultSet result) {
        try {
            Long id = result.getObject(1, Long.class);
            String name = result.getString("name");
            String licenseNumber = result.getString("licenseNumber");
            Driver driver = new Driver(name, licenseNumber);
            driver.setId(id);
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable make return from database", e);
        }
    }
}
