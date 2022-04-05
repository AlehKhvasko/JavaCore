package repository;

import java.sql.Connection;

public interface DBConnection {

    Connection connect(String dbname, String user, String password);

    void createTable(String name);

    void insert(String tableName, String city,String cityid);

    void read(String tableName);

    void update(int id, String tableName, String newValue, String oldValue);

    void delete(String city);

    void searchByCity(String city);

    void insertWeather(int city_key, String min_t, String max_t, String text);

    int getKeyById(int id);
}
