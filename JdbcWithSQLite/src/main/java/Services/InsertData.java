package Services;
import Repositories.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void insert(String... data) {
        String sql = "insert into weather(City, LocalDate, WeatherText, Temperature) value (?,?,?,?)";

        try (Connection con = DBConnection.connect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, data[1]);
            pstmt.setString(2, data[2]);
            pstmt.setString(3, data[3]);
            pstmt.setString(4, data[4]);
            pstmt.executeUpdate();
            System.out.println("Data has been added.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
