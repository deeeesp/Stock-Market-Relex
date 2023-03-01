package ru.stazaev.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SetExchangeRateDTO {
    private String secret_key;
    private String base_currency;
    @JsonProperty("RUB")
    private int RUB;
    @JsonProperty("BTC")
    private double BTC;
    @JsonProperty("TON")
    private double TON;
}
