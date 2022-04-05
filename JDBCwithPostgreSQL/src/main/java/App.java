import city.City;
import client.AccuWeatherClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.NoCityFound;
import repository.PostegreSQLRepositoryImpl;
import services.WeatherServiceImpl;
import weather.DailyForecast;
import weather.Root;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, NoCityFound {

        WeatherServiceImpl postgresDB = new WeatherServiceImpl(new PostegreSQLRepositoryImpl());
        Connection postgresConnection = postgresDB
                .getConnection("ApiWeather", "postgres", "19855891");

        //WeatherServiceImpl sqliteDB = new WeatherServiceImpl(new SQLiteRepositoryImpl());
        //Connection sqliteConnection = sqliteDB.getConnection("ApiWeather" ,"","");

        //PostgreSQL testing
        //postgresDB.createTable("top50cities");
        //postgresDB.insert("top50cities", "Dhaka","28143");
        //postgresDB.update(1, "top50cities", "name", "Austin");
        //postgresDB.searchByCity("austin");
        //postgresDB.deleteByCity("Austin");
        //postgresDB.read("top50cities");

        //SQLite testing
        //sqliteDB.createTable(sqliteConnection, "top50cities");
        //sqliteDB.delete(sqliteConnection, "Reykjavik");
        //sqliteDB.read(sqliteConnection, "top50cities");
        //sqliteDB.searchByCity(sqliteConnection, "Kabul");
        //System.out.println( sqliteDB.getKeyById(sqliteConnection, "1"));
        //sqliteDB.update(sqliteConnection, "top50cities", "city", "dhaka", "Dhaka");

        AccuWeatherClient accuWeatherClient = new AccuWeatherClient(new ObjectMapper());
        List<City> cityList = accuWeatherClient.get50TopCitiesList();
        postgresDB.showCities(cityList);

        //injecting data to SQLite
        //insertData(cityList, sqliteDB, sqliteConnection);

        //injecting data to a DB
        //postgresDB.insertData(cityList, postgresDB);


        System.out.println("Choose city you want to see from the list: ");

        postgresDB.read("top50cities");
        //sqliteDB.read(sqliteConnection, "top50cities");

        System.out.print("Input number of the city: => ");
        Scanner scanner = new Scanner(System.in);
        //TODO doesnt display cities correctly, ids don't match, try to use simple count.
        int usersChoice = scanner.nextInt() - 2;

        Root root = accuWeatherClient
                .getCurrentConditions(String.valueOf(postgresDB
                        .getKeyById(usersChoice)));


        System.out.println("\t Your choice is: " + cityList.get(usersChoice));
        System.out.println(root.headline);

        List<DailyForecast> forecastsArr = root.dailyForecasts;
        //TODO refactor streams
        postgresDB.insertWeather(postgresDB.getKeyById(usersChoice), forecastsArr
                        .stream()
                        .map((forecast) -> {
                            return forecast
                                    .getTemperature()
                                    .getMaximum()
                                    .getValue();
                        })
                        .findAny()
                        .orElseThrow(NoCityFound::new),
                forecastsArr
                        .stream()
                        .map((forecast) -> {
                            return forecast
                                    .getTemperature()
                                    .getMinimum()
                                    .getValue();
                        })
                        .findAny()
                        .orElseThrow(NoCityFound::new),
                root.headline.getText()
        );
    }
}
