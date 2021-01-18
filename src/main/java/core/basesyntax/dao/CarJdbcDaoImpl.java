package core.basesyntax.dao;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
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
public class CarJdbcDaoImpl implements CarDao {
    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT * FROM cars_drivers cd "
                + "JOIN drivers d on d.driver_id = cd.driver_id "
                + "JOIN cars c on c.car_id = cd.car_id "
                + "JOIN manufacturers m on m.manufacturer_id = c.manufacturer_id "
                + "WHERE cd.driver_id = ? AND c.deleted = FALSE AND d.deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement driversByCarStatement = connection.prepareStatement(query)) {
            driversByCarStatement.setLong(1, driverId);
            ResultSet result = driversByCarStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (result.next()) {
                cars.add(getCar(result, connection));
            }
            return cars;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get car by driver id " + driverId, e);
        }
    }

    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars(model, manufacturer_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement createdCar = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            createdCar.setString(1, car.getModel());
            createdCar.setLong(2, car.getManufacturer().getId());
            createdCar.executeUpdate();
            ResultSet resultSet = createdCar.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t create car " + car, e);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT * FROM cars c "
                + "JOIN manufacturers m on m.manufacturer_id = c.manufacturer_id "
                + "WHERE car_id = ? AND c.deleted = FALSE";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement gettingCar = connection.prepareStatement(query)) {
            gettingCar.setLong(1, id);
            ResultSet result = gettingCar.executeQuery();
            if (result.next()) {
                car = getCar(result, connection);
            }
            return Optional.ofNullable(car);
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t add car by id " + id, e);
        }
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT * FROM cars c "
                + "JOIN manufacturers m on m.manufacturer_id = c.manufacturer_id "
                + "WHERE c.deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement gettingAll = connection.prepareStatement(query)) {
            ResultSet result = gettingAll.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (result.next()) {
                cars.add(getCar(result, connection));
            }
            return cars;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get all cars", e);
        }
    }

    @Override
    public Car update(Car car) {
        String updateQuery = "UPDATE cars SET model = ?, manufacturer_id = ? "
                + "WHERE car_id = ? AND deleted = FALSE";
        String deleteQuery = "DELETE FROM cars_drivers WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement updatedCar = connection.prepareStatement(updateQuery);
                PreparedStatement deletedRelations = connection.prepareStatement(deleteQuery)) {
            updatedCar.setString(1, car.getModel());
            updatedCar.setLong(2, car.getManufacturer().getId());
            updatedCar.setLong(3, car.getId());
            updatedCar.executeUpdate();
            deletedRelations.setLong(1, car.getId());
            deletedRelations.executeUpdate();
            insertNewRelations(car, connection);
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t update car" + car, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET deleted = TRUE WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement deletedCar = connection.prepareStatement(query)) {
            deletedCar.setLong(1, id);
            return deletedCar.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t delete car by id " + id, e);
        }
    }

    private Car getCar(ResultSet result, Connection connection) throws SQLException {
        Long id = result.getObject(1, Long.class);
        String model = result.getString("model");
        Manufacturer manufacturer = getManufacturer(result);
        Car car = new Car(model, manufacturer);
        car.setId(id);
        car.setDrivers(getDrivers(id, connection));
        return car;
    }

    private List<Driver> getDrivers(Long carId, Connection connection) {
        String query = "SELECT * FROM cars_drivers cd "
                + "JOIN drivers d on d.driver_id = cd.driver_id "
                + "WHERE car_id = ? AND deleted = FALSE";
        try (PreparedStatement gettingDrivers = connection.prepareStatement(query)) {
            gettingDrivers.setLong(1, carId);
            ResultSet result = gettingDrivers.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (result.next()) {
                drivers.add(getDriver(result));
            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get drivers from database", e);
        }
    }

    private Manufacturer getManufacturer(ResultSet result) throws SQLException {
        Long id = result.getObject("manufacturer_id", Long.class);
        String name = result.getObject("name", String.class);
        String country = result.getObject("country", String.class);
        Manufacturer returnObject = new Manufacturer(name, country);
        returnObject.setId(id);
        return returnObject;
    }

    private Driver getDriver(ResultSet result) throws SQLException {
        Long id = result.getObject(1, Long.class);
        String name = result.getString("name");
        String licenseNumber = result.getString("licenseNumber");
        core.basesyntax.model.Driver driver = new Driver(name, licenseNumber);
        driver.setId(id);
        return driver;
    }

    private void insertNewRelations(Car car, Connection connection) {
        String query = "INSERT INTO cars_drivers(car_id, driver_id) VALUES (?, ?)";
        try (PreparedStatement insertedDriversByCar = connection.prepareStatement(query)) {
            for (Driver driver : car.getDrivers()) {
                insertedDriversByCar.setLong(1, car.getId());
                insertedDriversByCar.setLong(2, driver.getId());
                insertedDriversByCar.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t insert drivers to car" + car, e);
        }
    }
}
