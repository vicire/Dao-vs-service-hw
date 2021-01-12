package core.basesyntax.service;

import core.basesyntax.dao.CarDao;
import core.basesyntax.dao.DriverDao;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Inject
    private CarDao carDao;
    @Inject
    private DriverDao driverDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        return carDao.get(id).get();
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        if (!driverDao.getAll().contains(driver)) {
            driverDao.create(driver);
        }
        car.getDrivers().add(driver);
        carDao.update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().removeIf(driver1 -> Objects.equals(driver1, driver));
        carDao.update(car);
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
