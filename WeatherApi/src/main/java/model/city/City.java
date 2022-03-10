package model.city;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    @JsonProperty("Country")
    public Country country;
    @JsonProperty("LocalizedName")
    private String headline;
    @JsonProperty("Key")
    private Integer cityKey;

    public City(
    ) {
    }

    public Integer getCityKey() {
        return cityKey;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @Override
    public String toString() {
        return "City" + " " + headline;
    }
}

