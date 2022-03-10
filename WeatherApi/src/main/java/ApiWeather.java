import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.NoCityFound;
import model.weather.DailyForecast;
import model.weather.Root;
import repositories.RequestApi;
import model.city.City;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * System.out.println VS logger
 */
public class ApiWeather {
    public static void main(String[] args) throws IOException, NoCityFound {
    ArrayList<City> cityList;

    RequestApi requestCities = new RequestApi("locations", "topcities","50");
    String response = requestCities.getResponse();
    System.out.println(response);

    ObjectMapper objectMapper = defaultMapper();
    cityList = objectMapper.readValue(response, new TypeReference<>() {});

    System.out.println("Choose city you want to see from the list: ");
    cityMapping(cityList);
    System.out.print("Input number of the city: => ");
    Scanner scanner = new Scanner(System.in);
    int usersChoice = scanner.nextInt() - 1;
    String key = getLocalKey(usersChoice, cityList);

    RequestApi requestWeather = new RequestApi("forecasts", "daily", "5day", key);
    String weatherResponse = requestWeather.getResponse();
    ObjectMapper om = defaultMapper();
    Root root = om.readValue(weatherResponse, Root.class);
    System.out.println("\t Your choice is: " + cityList.get(usersChoice));
    System.out.println(root.headline);
    List<DailyForecast> forecastsArr = root.dailyForecasts;
        for (int i = 0; i < forecastsArr.size(); i++) {
            System.out.println(forecastsArr.get(i));
        }
    }

    private static void cityMapping (ArrayList<City> cityList) throws NoCityFound {
        Optional.ofNullable(cityList).orElseThrow(NoCityFound::new);
        for (int i = 0; i < cityList.size(); i++) {
            System.out.printf("%d. %s, %s %n", (i + 1), cityList.get(i), cityList.get(i).country);
        }
    }

    private static ObjectMapper defaultMapper(){
      return new ObjectMapper();
    }

    public static String getLocalKey(int id, ArrayList<City> cityList) throws NoCityFound {
        return Optional
                .of(String.valueOf(cityList
                        .get(id)
                        .getCityKey()))
                .orElseThrow(NoCityFound::new);
    }
}

