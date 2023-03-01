package ru.stazaev.entity.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponseDTO {
    private String currency_from;
    private String currency_to;
    private double amount_from;
    private double amount_to;
}
