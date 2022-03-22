package repository;

import java.sql.*;

public class SQLiteRepositoryImpl implements DBConnection {

    @Override
    public Connection connect(String dbname, String user, String password) {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:C:\\Users\\alehk\\OneDrive\\Desktop\\DummyDB\\ApiWeather.db";
            connection = DriverManager.getConnection(url);
            if (connection != null){
                System.out.println("SQLite connection has been established.");
            }else{
                System.err.println("SQLite connection failed.");
            }
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
        Statement stmt = null;
        String sql = "insert into top50cities (city, country, cityid) values (" + city + "," + country + "," + cityid + ")";
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Data has been added.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    @Override
    public void read(Connection con, String tableName) {
            String sql = "select * from " + tableName;

            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)){

                while (rs.next()){
                    System.out.println(rs.getString("empid") + " " +
                            rs.getString("city") +" " +
                            rs.getString("country") + " " +
                            rs.getString("cityid") + "\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
