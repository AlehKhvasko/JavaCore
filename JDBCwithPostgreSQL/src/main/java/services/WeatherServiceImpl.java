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

    public void update(Connection con, String tableName, String colomnName, String newValue, String oldValue){
        connection.update(con,tableName,colomnName,newValue,oldValue);
    }

    public void delete(Connection con,String city){
        connection.delete(con, city);
    }

    public void searchByCity(Connection con, String city){
        connection.searchByCity(con,city);
    }

    public String getKeyById(Connection con, String id){
        return connection.getKeyById(con, id);
    }
}