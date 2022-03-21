package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostegreSQLRepositoryImpl implements DBConnection {

    public Connection connect(String dbname, String user, String password) {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);
            if (con != null) {
                System.out.println("Connection established.");
            }else{
                System.err.println("Connection failed.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public void createTable(Connection con, String tableName){
        Statement statement;
        try {
            String query = "create table " + tableName + ("empid SERIAL, city varchar(200),country varchar(200), key int, primary key (empid));");
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert() {
        System.out.println("insert");
    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
