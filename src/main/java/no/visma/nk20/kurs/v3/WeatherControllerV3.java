package no.visma.nk20.kurs.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequiredArgsConstructor
public class WeatherControllerV3 {
    private WeatherRepository weatherRepository;

    @GetMapping("V3/now")
    public String getCurrent() {
        Weather weather = weatherRepository.findByTime(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));
        return formatToResponse(weather);
    }

    private String formatToResponse(Weather weather) {
        return String.format("Temperature: %s C, Wind speed: %s m/s, Rain: %s",
                weather.getTemperature(), weather.getWind(), weather.getRain());
    }

}