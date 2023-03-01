package ru.stazaev.entity.DTO.walletDto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TonWalletDTO extends WalletAbstractClass{
    private double TON_wallet;
}
