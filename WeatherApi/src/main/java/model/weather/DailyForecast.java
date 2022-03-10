package model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecast {
    @JsonProperty("Temperature")
    private Temperature temperature;
    @JsonProperty("Date")
    public Date date;

    public DailyForecast() {
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Date = " + date + "\n" + temperature;
    }
}
