package com.app.SportsFieldManagement.service;

import com.app.SportsFieldManagement.controller.WeatherClientController;
import com.app.SportsFieldManagement.dto.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {
    private final WeatherClientController weatherClientController;
    public double getCurrentTemperature(double latitude,double longitude){
        try {
            WeatherResponse response = weatherClientController.getCurrentWeather(latitude, longitude, true);
            if(response!=null && response.current_weather()!=null){
                return response.current_weather().temperature();
            }
        }
        catch(Exception e){
            log.error("Error occurred while getting weather information:{}",e);
        }
        return 15.0;
    }
    public boolean isWeatherSuitableForOutdoor(double latitude,double longitude){
        try {
            double temp = getCurrentTemperature(latitude, longitude);
            return temp > 10;
        }
        catch (Exception e){
            log.warn("Could not retrieve weather information. Assuming suitable weather by default.");
        }
        return true;
    }
}
