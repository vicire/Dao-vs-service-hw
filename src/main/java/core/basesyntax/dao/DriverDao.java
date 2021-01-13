package core.basesyntax.dao;

import core.basesyntax.model.Driver;
import java.util.List;
import java.util.Optional;

public interface DriverDao {

    Driver create(Driver driver);

    Optional<Driver> get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);
}
