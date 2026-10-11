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
    public WheatherResponse getWeather(String city){
          String finalAPI=appCache.APP_CACHE.get("WEATHER_API").replace(Placeholder.CITY,city).replace(Placeholder.API_KEY,apiKey);
          ResponseEntity<WheatherResponse> response=restTemplate.exchange(finalAPI,HttpMethod.GET,null,WheatherResponse.class);
         return response.getBody();
    
    }
}

