package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteRepositoryImpl implements DBConnection {

    @Override
    public Connection connect(String dbname, String user, String password) {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:C:\\Users\\alehk\\OneDrive\\Desktop\\DummyDB\\WeatherDB.db";
            connection = DriverManager.getConnection(url);
            System.out.println("SQLite connection has been established.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    @Override
    public void createTable(Connection con, String name) {

    }

    @Override
    public void insert(Connection con, String tableName, String city, String country, String cityid) {

    }


    @Override
    public void read(Connection con, String tableName) {

    }

    @Override
    public void update(Connection con, String tableName, String colomnName, String newValue, String oldValue) {

    }

    @Override
    public void delete(Connection con, String city) {

    }

    @Override
    public void searchByCity(Connection con, String city) {

    }

    @Override
    public String getKeyById(Connection con, String id) {
        return null;
    }
}
