package no.visma.nk20.kurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @GetMapping("/now")
    public String getWeatherForNow() {
        return "pent";
    }
}
