package core.basesyntax.dao;

import core.basesyntax.model.Manufacturer;
import java.util.List;
import java.util.Optional;

public interface ManufacturerDao {

    Manufacturer create(Manufacturer manufacturer);

    Optional<Manufacturer> get(Long id);

    List<Manufacturer> getAll();

    Manufacturer update(Manufacturer manufacturer);

    boolean delete(Long id);
}
