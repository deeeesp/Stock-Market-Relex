package ru.stazaev.entity.DTO.walletDto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TonAndRubWalletDTO extends WalletAbstractClass {
    private double TON_wallet;
    private double RUB_wallet;
}
