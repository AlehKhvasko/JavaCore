import Repositories.DBConnection;
import Services.InsertData;
import Services.ReadData;

public class App {
    public static void main(String[] args) {
        //InsertData.insert("Dallas", "25.01.22", "Sun", "45");
        ReadData.read();
    }
}
