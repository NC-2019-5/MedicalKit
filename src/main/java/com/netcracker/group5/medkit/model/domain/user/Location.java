package com.netcracker.group5.medkit.model.domain.user;

import java.util.Objects;

public class Location {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String houseNumber;

    public Location(Long id, String country, String city, String street, String houseNumber) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id.equals(location.id) &&
                Objects.equals(country, location.country) &&
                Objects.equals(city, location.city) &&
                Objects.equals(street, location.street) &&
                Objects.equals(houseNumber, location.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, houseNumber);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
