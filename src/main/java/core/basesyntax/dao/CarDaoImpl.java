package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Car;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        return Storage.cars.set(Storage.cars.indexOf(car), car);
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(car -> Objects.equals(car.getId(), id));
    }
}
