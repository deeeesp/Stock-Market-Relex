package ru.stazaev.entity.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletWithdrawal {
    private String secret_key;
    private String currency;
    private int count;
    private String credit_card;
    private String wallet;
}
