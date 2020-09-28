package no.visma.nk20.kurs.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("v3")
@RequiredArgsConstructor
public class WeatherControllerV3 {
    private final WeatherRepository weatherRepository;

    @GetMapping("now")
    public String getCurrent() {
        Weather weather = weatherRepository.findByTime(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1));
        return formatToResponse(weather);
    }

    private String formatToResponse(Weather weather) {
        return String.format("Temperature: %s C, Wind speed: %s m/s, Rain: %s",
                weather.getTemperature(),
                weather.getWind(),
                weather.getRain());
    }
}
