package com.app.SportsFieldManagement.dto.response;

public record WeatherResponse(CurrentWeather current_weather) {
    public record CurrentWeather(
            double temperature) {
    }
}
