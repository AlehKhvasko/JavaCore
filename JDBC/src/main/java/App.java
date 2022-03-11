import Repositoties.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
/*        insert("Douglas", "McGragery",
                "douglasMcgragar@gmail.com", "dogsNickName");
        insert("Mike", "Donkey",
                "donkey@gmail.com", "password");*/

        readAllDAta();
        readSpecificRow ();
    }

    public static void insert(String firstName, String secondName, String email, String password) {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO students (firstName, secondName, email, password) VALUES(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, secondName);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.execute();
            System.out.println("Data has been inserted!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void readAllDAta() {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM students";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String firstName = rs.getString("firstName");
                String secondName = rs.getString("secondName");
                String email = rs.getString("email");
                String password = rs.getString("password");

                System.out.println("All users");
                System.out.println("firstName " + firstName);
                System.out.println("secondName " + secondName);
                System.out.println("email " + email);
                System.out.println("password " + password);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public static void readSpecificRow () {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
                String sql = "Select firstName from students where email = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "douglasMcgragar@gmail.com");
                rs = ps.executeQuery();

                //reading one row

            String firstName = rs.getString(1);
            System.out.println(firstName);

            } catch (SQLException throwables) {
            throwables.printStackTrace();
           }finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}

