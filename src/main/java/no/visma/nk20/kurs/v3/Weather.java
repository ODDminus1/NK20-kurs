package no.visma.nk20.kurs.v3;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static no.visma.nk20.kurs.v3.Weather.TABLE_NAME;

@Data
@Entity
@Table(name = TABLE_NAME)
public class Weather {
    public static final String TABLE_NAME = "T_WEATHER";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private LocalDateTime time;
    private Double temperature;
    private Double wind;
    private Double rain;
}
