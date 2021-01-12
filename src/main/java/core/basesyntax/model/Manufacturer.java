package core.basesyntax.model;

import java.util.Objects;

public class Manufacturer {
    private long id;
    private String name;
    private String country;

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
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
                && (Objects.equals(name, manufacturer1.name))
                && (Objects.equals(id, manufacturer1.id)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return "{id " + id
                + "; model: " + name
                + "; produced country - " + country + "}";
    }
}
