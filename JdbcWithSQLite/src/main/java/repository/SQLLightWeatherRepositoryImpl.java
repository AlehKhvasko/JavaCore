package repository;

import java.sql.*;

public class SQLLightWeatherRepositoryImpl implements WeatherRepository {
    private Connection connection;

    public SQLLightWeatherRepositoryImpl(String url) {
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection has been established.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void insert(String... data) {
        String sql = "insert into Weather(City, LocalDate, Weather, Temperature) values(?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, data[0]);
            ps.setString(2, data[1]);
            ps.setString(3, data[2]);
            ps.setString(4, data[3]);
            ps.execute();
            System.out.println("Data has been added.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void read() {
        String sql = "select * from weather";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String city = rs.getString("City");
                String localDate = rs.getString("LocalDate");
                String weather = rs.getString("Weather");
                String temperature = rs.getString("Temperature");

                System.out.println(city + "\t" + localDate + "\t" + weather + "\t" + temperature);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
