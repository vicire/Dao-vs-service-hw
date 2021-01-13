package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class CarDaoImpl implements CarDao {

    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        return getAll().stream()
                .filter(car -> Objects.equals(car.getId(), id))
                .findFirst();
    }

    @Override
    public List<Car> getAll() {
        return new ArrayList<>(Storage.cars);
    }

    @Override
    public Car update(Car car) {
        Car carForCheck = get(car.getId()).get();
        return Storage.cars.set(Storage.cars.indexOf(carForCheck), car);
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(car -> Objects.equals(car.getId(), id));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return getAll().stream()
                .filter(car -> car.getDrivers()
                        .stream()
                        .map(Driver::getId)
                        .collect(Collectors.toList())
                        .contains(driverId))
                .collect(Collectors.toList());
    }
}
