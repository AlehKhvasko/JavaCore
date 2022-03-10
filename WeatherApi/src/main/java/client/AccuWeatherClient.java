package client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.city.City;
import model.weather.Root;
import client.utils.RequestApi;

import java.io.IOException;
import java.util.List;

public class AccuWeatherClient {

    private final ObjectMapper objectMapper;
    
    public AccuWeatherClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<City> get50TopCitiesList() throws IOException {
        RequestApi requestCities =
                new RequestApi("locations", "topcities", "50");
        String response = requestCities.getResponse();

        return objectMapper.readValue(response, new TypeReference<>() {
        });
    }

    public Root getCurrentConditions(String key) throws IOException {
        RequestApi requestWeather =
                new RequestApi("forecasts", "daily", "5day", key);
        String weatherResponse = requestWeather.getResponse();
        return objectMapper.readValue(weatherResponse, Root.class);
    }

}
