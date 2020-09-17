package no.visma.nk20.kurs;


import lombok.RequiredArgsConstructor;
import no.visma.nk20.kurs.domain.WeatherResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component
@RequiredArgsConstructor
public class Fetcher implements ApplicationListener<ContextRefreshedEvent> {
    private final RestTemplate restTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=51.5&lon=0"))
                .accept(MediaType.APPLICATION_JSON)
                .header(USER_AGENT, UUID.randomUUID().toString())
                .build();
        ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(requestEntity, WeatherResponse.class);
        System.out.println(exchange.getBody());
    }
}
