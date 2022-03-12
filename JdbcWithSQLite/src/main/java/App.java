import Repositories.DBConnection;
import Services.InsertData;

public class App {
    public static void main(String[] args) {
        InsertData.insert("Austin", "26.01.22", "Summer", "75");
    }
}
