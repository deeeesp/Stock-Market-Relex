package ru.stazaev.entity.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExchangeRequestDTO {
    private String secret_key;
    private String currency_from;
    private String currency_to;
    private double amount;

}
