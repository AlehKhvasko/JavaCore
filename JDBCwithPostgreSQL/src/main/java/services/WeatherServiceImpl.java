package services;
import repository.DBConnection;
import java.sql.Connection;

public class WeatherServiceImpl {
    private DBConnection connection;

    public WeatherServiceImpl(DBConnection connection){
        this.connection = connection;
    }

    public Connection getConnection(String DBname, String userName, String password) {
        return connection.connect( DBname,userName,password);
    }
}
