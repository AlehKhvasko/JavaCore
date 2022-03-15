package service;

import repository.WeatherRepository;

public class WeatherService {
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void insert(String... data) {
        weatherRepository.insert(data);
    }
    public void read() {
        weatherRepository.read();
    }
}
