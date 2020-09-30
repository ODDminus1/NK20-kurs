package no.visma.nk20.kurs.v2;

import no.visma.nk20.kurs.domain.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherControllerV2Test {
    @Mock
    private WeatherFetcher weatherFetcherMock;

    @InjectMocks
    private WeatherControllerV2 controller;

    @Test
    void test() {
        WeatherResponse.Properties.TimeSerie timeSerie = new WeatherResponse.Properties.TimeSerie();
        WeatherResponse.Properties.TimeSerie.WeatherData data = new WeatherResponse.Properties.TimeSerie.WeatherData();
        WeatherResponse.Properties.TimeSerie.WeatherData.Instant instant = new WeatherResponse.Properties.TimeSerie.WeatherData.Instant();
        WeatherResponse.Properties.TimeSerie.WeatherData.Instant.Details details = new WeatherResponse.Properties.TimeSerie.WeatherData.Instant.Details();
        details.setAirTemperature(-273.15);
        details.setCloudAreaFraction(101.);
        details.setWindSpeed(9001.);
        instant.setDetails(details);
        data.setInstant(instant);
        timeSerie.setData(data);
        when(weatherFetcherMock.getCurrent()).thenReturn(timeSerie);

        String result = controller.getCurrent();

        assertThat(result, containsString("-273.15"));
        assertThat(result, containsString("9001."));
    }
}