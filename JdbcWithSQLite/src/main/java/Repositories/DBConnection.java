package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect(){

        Connection connection = null;
        try{
            String url = "jdbc:sqlite:C:\\Users\\alehk\\OneDrive\\Desktop\\DummyDB\\WeatherDB";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection has been established.");
        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /**
         *
        Why do you need connection to be returned instead of void?
         */
        return connection;
    }
}
