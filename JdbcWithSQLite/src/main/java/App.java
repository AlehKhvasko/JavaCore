import Repositories.DBConnection;
import Services.InsertData;

public class App {
    public static void main(String[] args) {
        DBConnection.connect();
        InsertData.insert("Minsk", "26.01,22", "Raining", "75");
    }
}
