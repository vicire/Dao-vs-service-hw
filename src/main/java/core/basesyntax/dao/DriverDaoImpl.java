package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Dao
public class DriverDaoImpl implements DriverDao {

    @Override
    public Driver create(Driver driver) {
        Storage.addDriver(driver);
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return getAll().stream()
                .filter(driver -> Objects.equals(driver.getId(), id))
                .findFirst();
    }

    @Override
    public List<Driver> getAll() {
        return new ArrayList<>(Storage.drivers);
    }

    @Override
    public Driver update(Driver driver) {
        Driver driverForCheck = get(driver.getId()).get();
        return Storage.drivers.set(Storage.drivers.indexOf(driverForCheck), driver);
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.removeIf(driver -> Objects.equals(driver.getId(), id));
    }
}
