package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccuWeatherClientTest {

    AccuWeatherClient accuWeatherClient;

    @BeforeEach
    void beforeEach() {
        accuWeatherClient = new AccuWeatherClient(new ObjectMapper());
    }
    @Test
    void get50TopCitiesList() {

    }

    @Test
    void getCurrentConditions() {
    }
}