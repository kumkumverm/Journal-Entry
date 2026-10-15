package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.engineeringdigest.journalApp.Constants.Placeholder;
import net.engineeringdigest.journalApp.api.response.WheatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;

@Service
public class WeatherService {
  @Value("${weather.api.key}")
  private String apiKey;

  @Autowired
  private AppCache appCache;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisService redisService;

  public WheatherResponse getWeather(String city) {
    WheatherResponse weatherResponse = redisService.get("weather_of_" + city, WheatherResponse.class);
    if (weatherResponse != null)
      return weatherResponse;
    else {
      String weatherApi = appCache.APP_CACHE.get("WEATHER_API");

if (weatherApi == null || weatherApi.isEmpty()) {
    throw new IllegalStateException(
            "WEATHER_API is missing from the application cache");
}
      String finalAPI = appCache.APP_CACHE.get("WEATHER_API").replace(Placeholder.CITY, city)
          .replace(Placeholder.API_KEY, apiKey);
      ResponseEntity<WheatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null,
          WheatherResponse.class);
      WheatherResponse body = response.getBody();
      if (body != null) {
        redisService.set(
            "weather_of_" + city, body, 300l);
      }
      return body;
    }

  }
}
