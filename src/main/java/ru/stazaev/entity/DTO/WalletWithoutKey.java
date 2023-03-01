package ru.stazaev.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletWithoutKey {

    private double BTC_wallet;
    private double TON_wallet;
    private double RUB_wallet;
}
