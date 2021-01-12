package core.basesyntax;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.ManufacturerService;

public class Main {
    private static final Injector injector = Injector.getInstance(Main.class.getPackageName());

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);
        Manufacturer manufacturerAudi1 = new Manufacturer("Audi100", "Germany");
        Manufacturer manufacturerAudi2 = new Manufacturer("Audi A3", "Germany");
        Manufacturer manufacturerAlfa = new Manufacturer("Alfa Romeo", "Italy");
        manufacturerService.create(manufacturerAudi1);
        manufacturerService.create(manufacturerAudi2);
        manufacturerService.create(manufacturerAlfa);
        System.out.println(manufacturerService.getAll());
        Manufacturer updateManufacturer = manufacturerService.get(1L);
        updateManufacturer.setCountry("Austria");
        manufacturerService.update(updateManufacturer);
        System.out.println(manufacturerService.getAll());
        manufacturerService.delete(2L);
        System.out.println(manufacturerService.getAll());
    }
}
