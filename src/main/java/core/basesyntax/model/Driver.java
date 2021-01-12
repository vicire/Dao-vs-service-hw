package core.basesyntax.model;

import java.util.Objects;

public class Driver {
    private Long id;
    private String name;
    private String licenseNumber;

    public Driver(String name, String licenseNumber) {
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public boolean equals(Object driver) {
        if (this == driver) {
            return true;
        }
        if (driver == null || getClass() != driver.getClass()) {
            return false;
        }
        Driver driver1 = (Driver) driver;
        return (Objects.equals(name, driver1.name)
                && (Objects.equals(licenseNumber, driver1.licenseNumber))
                && (Objects.equals(id, driver1.id)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, licenseNumber);
    }
}
