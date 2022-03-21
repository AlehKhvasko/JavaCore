package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresSqlWeatherRepositoryImpl implements WeatherRepository{
    private Connection connection;
    public PostgresSqlWeatherRepositoryImpl() {
            String url = "jdbc:postgresql://localhost/ApiWeather";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","19855891");
            try {
                connection  = DriverManager.getConnection(url, props);
                System.out.println(" ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void insert(String... data) {

    }

    @Override
    public void read() {

    }
}
