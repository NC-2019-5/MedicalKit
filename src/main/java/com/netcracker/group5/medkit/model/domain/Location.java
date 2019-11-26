package com.netcracker.group5.medkit.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Location {
    private long id;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
}
