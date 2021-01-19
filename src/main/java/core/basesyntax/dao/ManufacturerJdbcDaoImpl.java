package core.basesyntax.dao;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.lib.Dao;
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
public class ManufacturerJdbcDaoImpl implements ManufacturerDao {

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query = "INSERT INTO manufacturers (brand, country) VALUE (?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement createdStatement = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            createdStatement.setString(1, manufacturer.getBrand());
            createdStatement.setString(2, manufacturer.getCountry());
            createdStatement.execute();
            ResultSet resultSet = createdStatement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t add manufacturer " + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long manufacturerId) {
        String query = "SELECT * FROM manufacturers WHERE manufacturer_id = ? AND deleted = false";
        Manufacturer manufacturer = null;
        try (Connection connection = ConnectionUtil.getConnection();
                    PreparedStatement gettingStatement = connection.prepareStatement(query)) {
            gettingStatement.setLong(1, manufacturerId);
            ResultSet result = gettingStatement.executeQuery();
            if (result.next()) {
                manufacturer = getManufacturer(result);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get manufacturer by id " + manufacturerId, e);
        }
        return Optional.ofNullable(manufacturer);
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM manufacturers WHERE deleted = false";
        List<Manufacturer> allDbObjects = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement gettingAllStatement = connection.prepareStatement(query)) {
            ResultSet result = gettingAllStatement.executeQuery();
            while (result.next()) {
                allDbObjects.add(getManufacturer(result));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get all manufacturers", e);
        }
        return allDbObjects;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE manufacturers SET brand = ?, country = ? "
                + "WHERE manufacturer_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement updatedStatement = connection.prepareStatement(query)) {
            updatedStatement.setString(1, manufacturer.getBrand());
            updatedStatement.setString(2, manufacturer.getCountry());
            updatedStatement.setLong(3, manufacturer.getId());
            updatedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t update manufacturer " + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long manufacturerId) {
        String query = "UPDATE manufacturers SET deleted = TRUE WHERE manufacturer_id = ?";
        int result = 0;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement deletedStatement = connection.prepareStatement(query)) {
            deletedStatement.setLong(1, manufacturerId);
            result = deletedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t delete manufacturer by id "
                    + manufacturerId, e);
        }
        return result > 0;
    }

    static Manufacturer getManufacturer(ResultSet result) {
        try {
            Long id = result.getObject(1, Long.class);
            String name = result.getString("brand");
            String country = result.getString("country");
            Manufacturer returnObject = new Manufacturer(name, country);
            returnObject.setId(id);
            return returnObject;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable make return from database", e);
        }
    }
}
