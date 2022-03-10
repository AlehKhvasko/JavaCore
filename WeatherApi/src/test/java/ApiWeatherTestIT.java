import client.AccuWeatherClient;
import client.utils.RequestApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.NoCityFound;
import model.city.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiWeatherTestIT {
    AccuWeatherClient accuWeatherClient;

    @BeforeEach
    void init() {
        accuWeatherClient = new AccuWeatherClient(new ObjectMapper());
    }

    @Test
    void getLocalKey_should_return_string_of_city() throws IOException, NoCityFound {
        //given
        List<City> cityList = accuWeatherClient.get50TopCitiesList();
        //when
        String result = App.getLocalKey(0, cityList);
        //then
        assertEquals("28143", result);
    }

}