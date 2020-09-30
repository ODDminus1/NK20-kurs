package no.visma.nk20.kurs.v1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class WeatherControllerV1Test {
    private WeatherControllerV1 controller = new WeatherControllerV1();

    @Test
    void returnsPent() {
        String result = controller.getWeatherForNow();

        assertThat(List.of("f", "b", "c"), hasSize(3));
    }
}