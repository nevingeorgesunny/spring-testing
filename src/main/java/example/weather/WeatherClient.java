package example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class WeatherClient {

    public static final String CITY = "Hamburg,de";
    private final RestTemplate restTemplate;
    private final String weatherServiceUrl;
    private final String weatherServiceApiKey;

    @Autowired
    public WeatherClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = "http://api.openweathermap.org/";
        this.weatherServiceApiKey = "5ee161c2acf7bb30037f1bf20ca89637";
    }

    public Optional<WeatherResponse> fetchWeather() {
        var url = String.format("%s/data/2.5/weather?q=%s&appid=%s", weatherServiceUrl, CITY, weatherServiceApiKey);

        try {
            return Optional.ofNullable(restTemplate.getForObject(url, WeatherResponse.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
