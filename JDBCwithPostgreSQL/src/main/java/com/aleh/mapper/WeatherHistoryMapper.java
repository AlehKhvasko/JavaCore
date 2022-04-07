package com.aleh.mapper;

import com.aleh.model.db.WeatherHistory;
import com.aleh.model.http.weather.Root;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherHistoryMapper {
    public static List<WeatherHistory> toWeatherHistory(Root root, long cityKey) {
        return root.dailyForecasts.stream()
                .map(dailyForecast -> new WeatherHistory(
                        cityKey,
                        dailyForecast.getTemperature().getMinimum().value,
                        dailyForecast.getTemperature().getMaximum().value,
                        root.getHeadline().getText())
                )
                .collect(Collectors.toList());
    }
}
