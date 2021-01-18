package core.basesyntax.dao;

import core.basesyntax.model.Car;
import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> getAllByDriver(Long driverId);
}
