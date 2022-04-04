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
    public static void main(String[] args) throws IOException {

        WeatherServiceImpl postgresDB = new WeatherServiceImpl(new PostegreSQLRepositoryImpl());
        Connection postgresConnection = postgresDB
                .getConnection("ApiWeather", "postgres", "19855891");

        //WeatherServiceImpl sqliteDB = new WeatherServiceImpl(new SQLiteRepositoryImpl());
        //Connection sqliteConnection = sqliteDB.getConnection("ApiWeather" ,"","");

        //PostgreSQL testing
        //postgresDB.createTable("top50cities");
        postgresDB.insert("top50cities", "Dhaka","28143");
        postgresDB.update(1, "top50cities", "name", "Austin");
        postgresDB.searchByCity("austin");
        postgresDB.deleteByCity("Austin");
        postgresDB.read("top50cities");

        //SQLite testing
        //sqliteDB.createTable(sqliteConnection, "top50cities");
        //sqliteDB.delete(sqliteConnection, "Reykjavik");
        //sqliteDB.read(sqliteConnection, "top50cities");
        //sqliteDB.searchByCity(sqliteConnection, "Kabul");
        //System.out.println( sqliteDB.getKeyById(sqliteConnection, "1"));
        //sqliteDB.update(sqliteConnection, "top50cities", "city", "dhaka", "Dhaka");

        //AccuWeatherClient accuWeatherClient = new AccuWeatherClient(new ObjectMapper());
        //List<City> cityList = accuWeatherClient.get50TopCitiesList();
        //postgresDB.showCities(cityList);

        //injecting data to SQLite
        //insertData(cityList, sqliteDB, sqliteConnection);

        //injecting data to a DB
        //insertData(cityList, postgresDB, postgresConnection);


        System.out.println("Choose city you want to see from the list: ");

        postgresDB.read("top50cities");
        //sqliteDB.read(sqliteConnection, "top50cities");

        System.out.print("Input number of the city: => ");
        Scanner scanner = new Scanner(System.in);
        //TODO doesnt display cities correctly, shifts to 2 numbers down.
        int usersChoice = scanner.nextInt() - 2;

        //Root root = accuWeatherClient
        //        .getCurrentConditions(postgresDB
        //               .getKeyById(postgresConnection,String.valueOf(usersChoice)));

        //System.out.println("\t Your choice is: " + cityList.get(usersChoice));
        //System.out.println(root.headline);

        //List<DailyForecast> forecastsArr = root.dailyForecasts;
        //for (DailyForecast forecast : forecastsArr) {
        //    System.out.println(forecast);
        //}
    }
}
