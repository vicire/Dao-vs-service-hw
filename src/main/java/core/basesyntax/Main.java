package core.basesyntax;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.CarService;
import core.basesyntax.service.DriverService;
import core.basesyntax.service.ManufacturerService;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance(Main.class.getPackageName());

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);
        Manufacturer manufacturerAudi1 = new Manufacturer("Audi", "Germany");
        Manufacturer manufacturerAudi2 = new Manufacturer("Audi", "Germany");
        Manufacturer manufacturerAlfa = new Manufacturer("Alfa Romeo", "Italy");
        Manufacturer manufacturerLincoln = new Manufacturer("Lincoln", "USA");
        manufacturerService.create(manufacturerAudi1);
        manufacturerService.create(manufacturerAudi2);
        manufacturerService.create(manufacturerAlfa);
        manufacturerService.create(manufacturerLincoln);
        System.out.println(manufacturerService.getAll());
        Manufacturer updateManufacturer = manufacturerService.get(1L);
        updateManufacturer.setCountry("Austria");
        manufacturerService.update(updateManufacturer);
        System.out.println(manufacturerService.getAll());
        manufacturerService.delete(2L);
        System.out.println(manufacturerService.getAll());
        System.out.println(" ");

        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        Driver driver1 = new Driver("Volodja", "959335932");
        Driver driver2 = new Driver("Bob", "14941752");
        Driver driver3 = new Driver("Alice", "8593205338");
        driverService.create(driver1);
        driverService.create(driver2);
        driverService.create(driver3);
        System.out.println(driverService.getAll());
        Driver updateDriver = driverService.get(2L);
        updateDriver.setName("John");
        driverService.update(updateDriver);
        System.out.println(driverService.getAll());
        driverService.delete(1L);
        System.out.println(driverService.getAll());
        System.out.println(" ");

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car carAustria = new Car("T100", manufacturerAudi1);
        Car carItaly = new Car("G3", manufacturerAlfa);
        Car carUsa = new Car("500", manufacturerLincoln);
        carService.create(carAustria);
        carService.create(carItaly);
        carService.create(carUsa);
        carService.addDriverToCar(driverService.get(2L), carService.get(1L));
        carService.addDriverToCar(driverService.get(3L), carService. get(2L));
        carService.addDriverToCar(driverService.get(3L), carService.get(1L));
        System.out.println(carService.getAll());
        Car updatedCar = carService.get(3L);
        updatedCar.setModel("A3");
        updatedCar.setDrivers(List.of(driver2, driver3));
        carService.update(updatedCar);
        System.out.println(carService.getAll());
        carService.delete(2L);
        carService.removeDriverFromCar(driverService.get(2L), carService.get(1L));
        System.out.println(carService.getAllByDriver(2L));
        System.out.println(carService.getAll());
    }
}
