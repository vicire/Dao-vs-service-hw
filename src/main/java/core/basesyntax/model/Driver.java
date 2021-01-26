package core.basesyntax.model;

import java.util.Objects;

public class Driver {
    private Long id;
    private String name;
    private String licenseNumber;
    private String login;
    private String password;

    public Driver(String name, String licenseNumber, String login, String password) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.login = login;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return Objects.equals(name, driver1.name)
                && Objects.equals(licenseNumber, driver1.licenseNumber)
                && Objects.equals(id, driver1.id) && Objects.equals(login, driver1.login)
                && Objects.equals(password, driver1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, licenseNumber, login, password);
    }

    @Override
    public String toString() {
        return "{Driver: "
                + "id " + id
                + ", name: " + name
                + ", number of license: " + licenseNumber
                + ", login: " + login + "}";
    }
}
