package repositories;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestApiTest {

    @Test
    void RequestApi_should_be_with_three_properties() throws IOException {
        //given
        final RequestApi requestApi  = new RequestApi("locations", "topcities","50");
        //when
        //then
        assertAll("Should contain proper http request",
                ()->{assertEquals("http",requestApi.protocolVersion);},
                ()->{assertEquals("dataservice.accuweather.com",requestApi.host);},
                ()->{assertEquals("locations",requestApi.firstParameter);},
                ()->{assertEquals("topcities",requestApi.secondParameter);},
                ()->{assertEquals("50",requestApi.thirdParameter);},
                ()->{assertEquals("MTmCyiM2DsHIkr5YYqWU5028EEa9r4hI",requestApi.getApi());}
        );

    }

    @Test
    void RequestApi_should_be_with_four_properties() throws IOException {
        //given
        RequestApi requestWeather = new RequestApi("forecasts", "daily", "5day", "28143");
        //then
        assertAll("Should contain proper http request",
                ()->{assertEquals("http",requestWeather.protocolVersion);},
                ()->{assertEquals("dataservice.accuweather.com",requestWeather.host);},
                ()->{assertEquals("forecasts",requestWeather.firstParameter);},
                ()->{assertEquals("daily",requestWeather.secondParameter);},
                ()->{assertEquals("5day",requestWeather.thirdParameter);},
                ()->{assertEquals("28143",requestWeather.fourthParameter);},
                ()->{assertEquals("MTmCyiM2DsHIkr5YYqWU5028EEa9r4hI",requestWeather.getApi());}
        );

    }
}