package model.city;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    @JsonProperty("LocalizedName")
    private String headline;

    public Integer getCityKey() {
        return cityKey;
    }


    @JsonProperty("Key")
    private Integer cityKey;

    @JsonProperty("Country")
    public  Country country;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public City(
    ) {
    }

    @Override
    public String toString() {
        return "City" + " " + headline;
    }
}

