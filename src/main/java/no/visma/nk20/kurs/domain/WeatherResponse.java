package no.visma.nk20.kurs.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WeatherResponse {
    String type;
    Properties properties;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        Meta meta;
        List<TimeSerie> timeseries;

        @Data
        public static class Meta {
            @JsonAlias("updated_at")
            LocalDateTime updatedAt;
        }

        @Data
        public static class TimeSerie {
            LocalDateTime time;
            WeatherData data;

            @Data
            public static class WeatherData {
                Instant instant;

                @Data
                public static class Instant {
                    Details details;

                    @Data
                    public static class Details {
                        @JsonAlias("air_temperature")
                        Double airTemperature;
                        @JsonAlias("cloud_area_fraction")
                        Double cloudAreaFraction;
                        @JsonAlias("wind_speed")
                        Double windSpeed;
                    }
                }
            }
        }
    }
}
