import city.City;
import client.AccuWeatherClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.NoCityFound;
import repository.PostegreSQLRepositoryImpl;
import repository.SQLiteRepositoryImpl;
import services.WeatherServiceImpl;
import weather.DailyForecast;
import weather.Root;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {

        //WeatherServiceImpl postgresDB = new WeatherServiceImpl(new PostegreSQLRepositoryImpl());
        //Connection postgresConnection = postgresDB
        //        .getConnection("ApiWeather", "postgres", "19855891");

        WeatherServiceImpl sqliteDB = new WeatherServiceImpl(new SQLiteRepositoryImpl());
        Connection sqliteConnection = sqliteDB.getConnection("ApiWeather" ,"","");

        //PostgreSQL testing
        //postgresDB.createTable(postgresConnection, "top50cities");
        //postgresDB.insert(postgresConnection, "top50cities", "Dhaka", "Asia", "28143");
        //postgresDB.update(postgresConnection, "top50cities", "city", "Austin", "Dhaka" );
        //postgresDB.searchByCity(postgresConnection, "Austin");
        //postgresDB.delete(postgresConnection, "Austin");
        //postgresDB.read(postgresConnection, "top50cities");

        //SQLite testing
        //sqliteDB.createTable(sqliteConnection, "top50cities");
        //AccuWeatherClient accuWeatherClient = new AccuWeatherClient(new ObjectMapper());
        //List<City> cityList = accuWeatherClient.get50TopCitiesList();

        //injecting data to SQLite
        //insertData(cityList, sqliteDB, sqliteConnection);
        sqliteDB.read(sqliteConnection, "top50cities");


        //injecting data to a DB
        //insertData(cityList, postgresDB, postgresConnection);

        /*System.out.println("Choose city you want to see from the list: ");
        postgresDB.read(postgresConnection, "top50cities");

        System.out.print("Input number of the city: => ");
        Scanner scanner = new Scanner(System.in);
        //TODO doesnt display cities correctly, shifts to 2 numbers down.
        int usersChoice = scanner.nextInt() - 2;

        Root root = accuWeatherClient
                .getCurrentConditions(postgresDB
                        .getKeyById(postgresConnection,String.valueOf(usersChoice)));

        System.out.println("\t Your choice is: " + cityList.get(usersChoice));
        System.out.println(root.headline);

        List<DailyForecast> forecastsArr = root.dailyForecasts;
        for (DailyForecast forecast : forecastsArr) {
            System.out.println(forecast);
        }*/
    }

    private static void showCities(List<City> cityList) throws NoCityFound {
        if (cityList.isEmpty()) {
            throw new NoCityFound();
        }
        for (int i = 0; i < cityList.size(); i++) {
            System.out.printf("%d. %s, %s %n", (i + 1), cityList.get(i), cityList.get(i).country);
        }
    }

    private static void insertData(List<City> cityList, WeatherServiceImpl postgresDB, Connection postgresConnection) {
        cityList.stream().forEach(city -> postgresDB
                .insert(postgresConnection, "top50cities",
                        city.getCityName(),
                        city.getCountry().getLocalName(),
                        String.valueOf(city.getCityKey())));
    }
}
