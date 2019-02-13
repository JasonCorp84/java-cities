package com.lambdaschoo.cities.restcities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

public class City {
    private @Id @GeneratedValue long id;
    private String name;
    private String state;
    private Long medianHomePrices;
    private int affordabilityIndex;

    public City(){

    }

    public City(String name, String state, Long medianHomePrices, int affordabilityIndex) {
        this.name = name;
        this.state = state;
        this.medianHomePrices = medianHomePrices;
        this.affordabilityIndex = affordabilityIndex;
    }
}
