package com.aleh.repository;

import com.aleh.model.db.WeatherHistory;

public interface RepositoryI {

    void insert(String tableName, String city, String cityid);

    void read(String tableName);

    void update(int id, String tableName, String newValue, String oldValue);

    void delete(String city);

    void searchByCity(String city);

    void insertWeather(int city_key, String min_t, String max_t, String text);

    void insertWeather(WeatherHistory weatherHistory);

    int getKeyById(int id);

}
