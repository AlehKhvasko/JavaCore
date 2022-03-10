package model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Minimum {
    @JsonProperty("Value")
    public int value;
    @JsonProperty("Unit")
    public String unit;

    @Override
    public String toString() {
        return value + unit + ", ";
    }
}
