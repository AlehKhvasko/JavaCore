package repository;

import java.sql.*;

public class PostegreSQLRepositoryImpl implements DBConnection {

    public Connection connect(String dbname, String user, String password) {
        Connection con = null;
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

    public void createTable(Connection con, String tableName) {
        Statement statement;
        try {
            String query = "create table " + tableName + "(empid SERIAL , city varchar(200)," +
                    " country varchar(200), cityID int, primary key (empid));";

            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Connection con, String tableName, String city, String country, String cityid) {
        Statement statement;
            String query = String.format("insert into %s(city, country, cityid) values('%s','%s', %s);",
                    tableName, city, country, cityid);

        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data has been inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void read(Connection con, String tableName) {
        Statement statement;
        ResultSet rs;
        try {
            String query = String.format("select * from %s", tableName);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("city") + " ");
                System.out.print(rs.getString("country") + " ");
                System.out.print(rs.getString("cityid") + " ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
