package no.visma.nk20.kurs.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherControllerV1 {
    @GetMapping("v1/now")
    public String getWeatherForNow() {
        return "pent";
    }
}
