package com.lambdaschoo.cities.restcities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity

public class City {
    private @Id @GeneratedValue long id;
    private String name;

    private int medianHomePrices;
    private int affordabilityIndex;

    public City(){

    }

    public City(String name, int medianHomePrices, int affordabilityIndex) {
        this.name = name;
        this.medianHomePrices = medianHomePrices;
        this.affordabilityIndex = affordabilityIndex;
    }
}
