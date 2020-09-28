package no.visma.nk20.kurs.v2;

import lombok.RequiredArgsConstructor;
import no.visma.nk20.kurs.domain.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v2")
@RequiredArgsConstructor
public class WeatherControllerV2 {
    private final WeatherFetcher weatherFetcher;

    @GetMapping("now")
    public String getCurrent() {
        return formatToRespose(weatherFetcher.getCurrent());
    }

    private String formatToRespose(WeatherResponse.Properties.TimeSerie current) {
        return String.format("Temperature: %s C, Wind speed: %s m/s",
                current.getData().getInstant().getDetails().getAirTemperature(),
                current.getData().getInstant().getDetails().getWindSpeed()
        );
    }

}
