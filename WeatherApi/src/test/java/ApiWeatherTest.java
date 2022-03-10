import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.NoCityFound;
import model.city.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.RequestApi;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiWeatherTest {
    ObjectMapper objectMapper;
    @BeforeEach
    void init(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void getLocalKey_should_return_string_of_city() throws IOException, NoCityFound {
        //given
        String expectedKey = "28143";
        RequestApi requestCities = new RequestApi("locations", "topcities","50");
        String response = requestCities.getResponse();
        ArrayList<City> cityList = objectMapper.readValue(response, new TypeReference<>() {});

        //when
        String result = ApiWeather.getLocalKey(0, cityList);

        //then
        assertEquals(expectedKey, result);
    }

}