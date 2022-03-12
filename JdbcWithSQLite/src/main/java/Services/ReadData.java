package Services;

import Repositories.DBConnection;

import java.sql.*;

public class ReadData {
    public static void read(){
        String sql = "select * from weather";

        try (Connection con = DBConnection.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)){

            while (rs.next()){
                System.out.println(rs.getString("City") + "\t" +
                        rs.getString("LocalDate") +"\t" +
                        rs.getString("Weather") + "\t" +
                        rs.getString("Temperature"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
