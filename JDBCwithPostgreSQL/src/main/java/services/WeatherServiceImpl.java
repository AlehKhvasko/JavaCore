package services;

import city.City;
import exceptions.NoCityFound;
import repository.DBConnection;

import java.sql.Connection;
import java.util.List;

public class WeatherServiceImpl {
    private final DBConnection connection;

    public WeatherServiceImpl(DBConnection connection) {
        this.connection = connection;
    }

    public Connection getConnection(String DBname, String userName, String password) {
        return connection.connect(DBname, userName, password);
    }

    public void createTable(String name) {
        connection.createTable(name);
    }

    public void insert(String tableName, String city, String cityid) {
        connection.insert(tableName, city, cityid);
    }

    public void read(String table){
        connection.read(table);
    }

    public void update(int id, String colomnName, String newValue, String oldValue){
        connection.update(id,colomnName,newValue,oldValue);
    }

    public void deleteByCity(String city){
        connection.delete(city);
    }

    public void searchByCity(String city){
        connection.searchByCity(city);
    }

    public String getKeyById(String id){
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

    public void insertData(List<City> cityList, WeatherServiceImpl postgresDB) {
        cityList.stream().forEach(city -> postgresDB
                .insert("top50cities",
                        city.getCityName(),
                        String.valueOf(city.getCityKey())));
    }
}