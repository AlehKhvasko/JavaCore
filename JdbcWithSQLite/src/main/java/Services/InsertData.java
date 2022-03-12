package Services;

import Repositories.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void insert(String ... data) {

        String sql = "insert into Weather(City, LocalDate, Weather, Temperature) values(?,?,?,?)";
        try (Connection con = DBConnection.connect();
                PreparedStatement ps = con.prepareStatement(sql)){
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
}
