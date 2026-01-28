package com.app.SportsFieldManagement.controller;

import com.app.SportsFieldManagement.dto.response.WeatherResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url="https://api.open-meteo.com/v1")
public interface WeatherClientController {
    @GetExchange("/forecast")
    WeatherResponse getCurrentWeather(@RequestParam double latitude,
                                      @RequestParam double longitude,
                                      @RequestParam(name = "current_weather") boolean currentWeather);
}
