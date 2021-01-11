package core.basesyntax.db;

import core.basesyntax.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Manufacturer> manufacturerStorage = new ArrayList<>();
    private static long manufacturerId = 0;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturerId);
        manufacturerStorage.add(manufacturer);
    }
}
