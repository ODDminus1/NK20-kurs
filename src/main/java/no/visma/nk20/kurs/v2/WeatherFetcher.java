package no.visma.nk20.kurs.v2;

import lombok.RequiredArgsConstructor;
import no.visma.nk20.kurs.domain.WeatherResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component
@RequiredArgsConstructor
public class WeatherFetcher {
    private final RestTemplate restTemplate;
    private static final String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=%s&lon=%s";

    public WeatherResponse.Properties.TimeSerie getCurrent() {
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(String.format(url, 51.5, 0)))
                .header(USER_AGENT, UUID.randomUUID().toString())
                .build();
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(requestEntity, WeatherResponse.class);
        return responseEntity.getBody().getProperties()
                .getTimeseries().stream().filter(t -> t.getTime().equals(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1))).findFirst().get();
    }
}
