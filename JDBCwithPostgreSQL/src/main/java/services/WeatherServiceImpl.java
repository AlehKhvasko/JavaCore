package services;

import repository.DBConnection;

import java.sql.Connection;

public class WeatherServiceImpl {
    private final DBConnection connection;

    public WeatherServiceImpl(DBConnection connection) {
        this.connection = connection;
    }

    public Connection getConnection(String DBname, String userName, String password) {
        return connection.connect(DBname, userName, password);
    }

    public void createTable(Connection con, String name) {
        connection.createTable(con, name);
    }

    public void insert(Connection con, String tableName, String city, String country, String cityid) {
        connection.insert(con, tableName, city, country, cityid);
    }

    public void read(Connection con, String table){
        connection.read(con,table);
    }
}