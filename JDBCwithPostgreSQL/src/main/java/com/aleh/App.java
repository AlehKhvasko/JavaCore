package com.aleh;

import com.aleh.mapper.WeatherHistoryMapper;
import com.aleh.model.db.WeatherHistory;
import com.aleh.model.http.city.City;
import com.aleh.httpclient.AccuWeatherClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aleh.exception.NoCityFound;
import com.aleh.repository.PostegreSQLRepositoryImpl;
import com.aleh.service.WeatherServiceImpl;
import com.aleh.model.http.weather.DailyForecast;
import com.aleh.model.http.weather.Root;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, NoCityFound {
        WeatherServiceImpl postgresDB = new WeatherServiceImpl(
                new PostegreSQLRepositoryImpl());

        //WeatherServiceImpl sqliteDB = new WeatherServiceImpl(new SQLiteRepositoryImpl());

        //PostgreSQL testing
//        postgresDB.insert("top50cities", "Dhaka","28143");
//        postgresDB.update(1, "top50cities", "name", "Austin");
//        postgresDB.searchByCity("austin");
//        postgresDB.deleteByCity("Austin");
//        postgresDB.read("top50cities");

        //SQLite testing
        //sqliteDB.createTable(sqliteConnection, "top50cities");
        //sqliteDB.delete(sqliteConnection, "Reykjavik");
        //sqliteDB.read(sqliteConnection, "top50cities");
        //sqliteDB.searchByCity(sqliteConnection, "Kabul");
        //System.out.println( sqliteDB.getKeyById(sqliteConnection, "1"));
        //sqliteDB.update(sqliteConnection, "top50cities", "com.aleh.model.http.city", "dhaka", "Dhaka");

        AccuWeatherClient accuWeatherClient = new AccuWeatherClient(new ObjectMapper());
        List<City> cityList = accuWeatherClient.get50TopCitiesList();
        postgresDB.showCities(cityList);

        //injecting data to SQLite
        //insertData(cityList, sqliteDB, sqliteConnection);

        //injecting data to a DB
        //postgresDB.insertData(cityList, postgresDB);


        System.out.println("Choose com.aleh.model.http.city you want to see from the list: ");

        postgresDB.read("top50cities");
        //sqliteDB.read(sqliteConnection, "top50cities");

        System.out.print("Input number of the com.aleh.model.http.city: => ");
        Scanner scanner = new Scanner(System.in);
        //TODO doesnt display cities correctly, ids don't match, try to use simple count.

        String key = scanner.next();
        Root root = accuWeatherClient.getCurrentConditions(key);

        int index = Integer.parseInt(key);

        System.out.println("\t Your choice is: " + cityList.get(index));
        System.out.println(root.headline);

        //WeatherHistory
        WeatherHistoryMapper.toWeatherHistory(root, index)
                        .forEach(postgresDB::insertWeather);

//        //TODO refactor streams
//        postgresDB.insertWeather(index, forecastsArr
//                        .stream()
//                        .map((forecast) -> {
//                            return forecast
//                                    .getTemperature()
//                                    .getMaximum()
//                                    .getValue();
//                        })
//                        .findAny()
//                        .orElseThrow(NoCityFound::new),
//                forecastsArr
//                        .stream()
//                        .map((forecast) -> {
//                            return forecast
//                                    .getTemperature()
//                                    .getMinimum()
//                                    .getValue();
//                        })
//                        .findAny()
//                        .orElseThrow(NoCityFound::new),
//                root.headline.getText()
//        );
    }
}
