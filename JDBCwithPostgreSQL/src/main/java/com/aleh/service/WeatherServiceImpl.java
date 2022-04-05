package com.aleh.service;

import com.aleh.model.db.WeatherHistory;
import com.aleh.model.http.city.City;
import com.aleh.exception.NoCityFound;
import com.aleh.repository.RepositoryI;

import java.util.List;

public class WeatherServiceImpl {
    private final RepositoryI connection;

    public WeatherServiceImpl(RepositoryI connection) {
        this.connection = connection;
    }

    public void insert(String tableName, String city, String cityid) {
        connection.insert(tableName, city, cityid);
    }

    public void read(String table){
        connection.read(table);
    }

    public void update(int id, String columnName, String newValue, String oldValue){
        connection.update(id,columnName,newValue,oldValue);
    }

    public void deleteByCity(String city){
        connection.delete(city);
    }

    public void searchByCity(String city){
        connection.searchByCity(city);
    }

    public int getKeyById(int id){
        return connection.getKeyById(id);
    }

    public void showCities(List<City> cityList) {
        if (cityList.isEmpty()) {
            try {
                throw new NoCityFound();
            } catch (NoCityFound noCityFound) {
                noCityFound.printStackTrace();
            }
        }
        for (int i = 0; i < cityList.size(); i++) {
            System.out.printf("%d. %s, %s %n", (i + 1), cityList.get(i), cityList.get(i).country);
        }
    }

    public void insertWeather(int city_key, String min_t, String max_t, String text){
        connection.insertWeather(city_key, min_t, max_t, text);
    }

    public void insertWeather(WeatherHistory weatherHistory) {
        //cheack if not exist
        connection.insertWeather(weatherHistory);
    }

    public void insertData(List<City> cityList, WeatherServiceImpl postgresDB) {
        cityList.stream().forEach(city -> postgresDB
                .insert("top50cities",
                        city.getCityName(),
                        String.valueOf(city.getCityKey())));
    }
}