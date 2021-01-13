package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return getAll().stream()
                .filter(manufacturer -> Objects.equals(manufacturer.getId(),id))
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return new ArrayList<>(Storage.manufacturers);
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        Manufacturer manufacturerForCheck = get(manufacturer.getId()).get();
        return Storage.manufacturers
                    .set(Storage.manufacturers.indexOf(manufacturerForCheck), manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(i -> Objects.equals(i.getId(), id));
    }
}
