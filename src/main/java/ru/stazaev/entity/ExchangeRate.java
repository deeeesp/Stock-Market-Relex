package ru.stazaev.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonProperty("RUB")
    private double RUB;

    @Column
    @JsonProperty("TON")
    private double TON;

    @Column
    @JsonProperty("BTC")
    private double BTC;

    public ExchangeRate(double RUB, double TON, double BTC) {
        this.RUB = RUB;
        this.TON = TON;
        this.BTC = BTC;
    }
}
