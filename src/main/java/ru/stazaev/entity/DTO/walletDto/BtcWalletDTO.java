package ru.stazaev.entity.DTO.walletDto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BtcWalletDTO extends WalletAbstractClass{
    private double BTC_wallet;
}
