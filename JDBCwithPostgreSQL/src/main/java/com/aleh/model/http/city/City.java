package com.aleh.model.http.city;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    @JsonProperty("Country")
    public Country country;
    @JsonProperty("LocalizedName")
    private String cityName;
    @JsonProperty("Key")
    private Integer cityKey;

    public City(
    ) {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getCityKey() {
        return cityKey;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City" + " " + cityName;
    }
}

