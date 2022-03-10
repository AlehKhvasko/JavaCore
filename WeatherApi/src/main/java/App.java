import client.AccuWeatherClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.NoCityFound;
import model.city.City;
import model.weather.DailyForecast;
import model.weather.Root;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, NoCityFound {
        AccuWeatherClient accuWeatherClient = new AccuWeatherClient(new ObjectMapper());
        List<City> cityList = accuWeatherClient.get50TopCitiesList();

        System.out.println("Choose city you want to see from the list: ");
        showCities(cityList);

        System.out.print("Input number of the city: => ");
        Scanner scanner = new Scanner(System.in);
        int usersChoice = scanner.nextInt() - 1;

        String key = getLocalKey(usersChoice, cityList);
        Root root = accuWeatherClient.getCurrentConditions(key);

        System.out.println("\t Your choice is: " + cityList.get(usersChoice));
        System.out.println(root.headline);

        List<DailyForecast> forecastsArr = root.dailyForecasts;
        for (DailyForecast forecast : forecastsArr) {
            System.out.println(forecast);
        }
    }

    private static void showCities(List<City> cityList) throws NoCityFound {
        if (cityList.isEmpty()) {
            throw new NoCityFound();
        }
        for (int i = 0; i < cityList.size(); i++) {
            System.out.printf("%d. %s, %s %n", (i + 1), cityList.get(i), cityList.get(i).country);
        }
    }

    public static String getLocalKey(Integer id, List<City> cityList) throws NoCityFound {
        return cityList.stream()
                .filter(city -> !city.getCityKey().equals(id))
                .findFirst()
                .map(city -> city.getCityKey().toString())
                .orElseThrow(NoCityFound::new);
    }
}

