package core.basesyntax.model;

import java.util.Objects;

public class Manufacturer {
    private Long id;
    private String brand;
    private String country;

    public Manufacturer(String brand, String country) {
        this.brand = brand;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object manufacturer) {
        if (this == manufacturer) {
            return true;
        }
        if (manufacturer == null || getClass() != manufacturer.getClass()) {
            return false;
        }
        Manufacturer manufacturer1 = (Manufacturer) manufacturer;
        return (Objects.equals(country, manufacturer1.country)
                && (Objects.equals(brand, manufacturer1.brand))
                && (Objects.equals(id, manufacturer1.id)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, country);
    }

    @Override
    public String toString() {
        return "{id " + id
                + "; model: " + brand
                + "; produced country - " + country + "}";
    }
}
