import repository.SQLLightWeatherRepositoryImpl;
import repository.WeatherRepository;
import service.WeatherService;

public class App {
    public static void main(String[] args) {
        String url = args[0];

        WeatherService weatherService =
                new WeatherService(
                        new SQLLightWeatherRepositoryImpl(url));

        weatherService.insert("Dallas", "25.01.22", "Sun", "45");
        weatherService.read();
    }
}
