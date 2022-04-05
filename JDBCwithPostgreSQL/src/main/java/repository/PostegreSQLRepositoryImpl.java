package repository;

import java.sql.*;

public class PostegreSQLRepositoryImpl implements DBConnection {
    Connection con = null;

    public Connection connect(String dbname, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);
            if (con != null) {
                System.out.println("Connection established.");
            } else {
                System.err.println("Connection failed.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public void createTable(String tableName) {
        Statement statement;
        try {
            String query = "create table " + tableName + "(id SERIAL , name varchar(200)," +
                    " cityID int, primary key (id));";

            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(String tableName, String city, String cityid) {
        Statement statement;
        String query = String.format("insert into %s(name, cityid) values('%s', %s);",
                tableName, city, cityid);

        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data has been inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertWeather(int city_key, String min_t, String max_t, String text) {
        Statement statement;
        String query = String.format("insert into weather_history(city_key, min_t, max_t, text)" +
                        " values(%s, %s, %s, '%s');", city_key, min_t, max_t, text);
        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Weather data has been inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void read(String tableName) {
        Statement statement;
        ResultSet rs;
        try {
            String query = String.format("select * from %s", tableName);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getString("cityid") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, String tableName, String columnName, String updatedValue) {
        Statement statement;
        try {
            String query = String.format("update %s set %s='%s' where id ='%s'",
                    tableName, columnName, columnName, id);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data has been updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(String city) {
        Statement statement;
        try {
            String query = String.format("delete from top50cities where name='%s'", city);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println(city + " has been deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchByCity(String city) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from top50cities where name= '%s'", city);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("city") + " ");
                System.out.print(rs.getString("country") + " ");
                System.out.print(rs.getString("cityid") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getKeyById(int id) {
        Statement statement;
        ResultSet rs = null;
        int cityKey = 0;
        try {
            String query = String.format("select * from top50cities where id= '%s'", id);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                cityKey = rs.getInt("cityid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityKey;
    }
}
