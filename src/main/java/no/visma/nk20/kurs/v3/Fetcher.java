package no.visma.nk20.kurs.v3;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.visma.nk20.kurs.domain.WeatherResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Slf4j
@Component
@RequiredArgsConstructor
public class Fetcher {
    private static final String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=%s&lon=%s";
    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    @PostConstruct
    @Scheduled(cron = "0 0 * * * *")
    public void onApplicationEvent() {
        log.info("Fetching weather data.");
        Instant tick = Instant.now();
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(String.format(url, 51.5, 0)))
                .header(USER_AGENT, UUID.randomUUID().toString())
                .build();
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(requestEntity, WeatherResponse.class);
        WeatherResponse.Properties.TimeSerie timeSerie = responseEntity.getBody().getProperties().getTimeseries().stream()
                .filter(t -> t.getTime().isAfter(LocalDateTime.now()))
                .min(Comparator.comparing(WeatherResponse.Properties.TimeSerie::getTime)).orElseThrow(RuntimeException::new);
        Weather weather = mapToWeather(timeSerie);
        weatherRepository.save(weather);
        log.info("Done fetching weather data. Ellapsed time: {} ms", Instant.now().toEpochMilli() - tick.toEpochMilli());
    }

    private Weather mapToWeather(WeatherResponse.Properties.TimeSerie timeSerie) {
        return Weather.builder()
                .time(timeSerie.getTime())
                .temperature(timeSerie.getData().getInstant().getDetails().getAirTemperature())
                .rain(timeSerie.getData().getNextHour().getDetails().getRegn())
                .wind(timeSerie.getData().getInstant().getDetails().getWindSpeed())
                .build();
    }
}
