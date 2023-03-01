package ru.stazaev.entity.DTO.walletDto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RubWalletDTO extends WalletAbstractClass{
    private double RUB_wallet;
}
