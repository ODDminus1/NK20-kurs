package no.visma.nk20.kurs.v3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static no.visma.nk20.kurs.v3.Weather.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME)
public class Weather {
    public static final String TABLE_NAME = "T_WEATHER";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime time;
    private Double temperature;
    private Double wind;
    private Double rain;
}
