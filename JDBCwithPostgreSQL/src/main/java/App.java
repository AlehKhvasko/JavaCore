import repository.PostegreSQLRepositoryImpl;
import services.WeatherServiceImpl;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        WeatherServiceImpl dbTest = new WeatherServiceImpl(new PostegreSQLRepositoryImpl());
        Connection postgres = dbTest.getConnection("ApiWeather", "postgres", "19855891");

    }
}
