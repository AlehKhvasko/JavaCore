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
        String query = "CREATE TABLE IF NOT EXISTS " + name +" (\n"
            + " empid integer primary key autoincrement, \n"
            + " city text NOT NULL, \n"
            + " country text, \n"
            + " cityid text\n"
            + ");";

        try (Statement stmt = con.createStatement()){
            stmt.execute(query);
            System.out.println("Table " + name + " has been added.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(Connection con, String tableName, String city, String country, String cityid) {
        Statement stmt;
        String query = "insert into " + tableName + "(city, country, cityid) values (?,?,?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, city);
            pstmt.setString(2, country);
            pstmt.setString(3, cityid);
            pstmt.executeUpdate();
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
