import repository.PostegreSQLRepositoryImpl;
import services.WeatherServiceImpl;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {

        WeatherServiceImpl postgresDB = new WeatherServiceImpl(new PostegreSQLRepositoryImpl());
        Connection postgresConnection = postgresDB
                .getConnection("ApiWeather", "postgres", "19855891");

        //postgresDB.createTable(postgresConnection, "top50cities");
        postgresDB.insert(postgresConnection, "top50cities", "Dhaka", "Asia", 28143);





    }
}
