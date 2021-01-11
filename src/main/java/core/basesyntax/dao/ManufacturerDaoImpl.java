package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Manufacturer;
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
        return Storage.manufacturerStorage;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        Manufacturer oldManufacturer = get(manufacturer.getId()).get();
        oldManufacturer.setCountry(manufacturer.getCountry());
        oldManufacturer.setName(manufacturer.getName());
        return oldManufacturer;
    }

    @Override
    public boolean delete(Long id) {
        if (get(id).isPresent()) {
            return Storage.manufacturerStorage.remove(get(id).get());
        }
        return false;
    }
}
