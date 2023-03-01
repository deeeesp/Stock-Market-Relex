package ru.stazaev.entity.DTO.walletDto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BtcAndRubWalletDTO extends WalletAbstractClass{
    private double RUB_wallet;
    private double BTC_wallet;
}
