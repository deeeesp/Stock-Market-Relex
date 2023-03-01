package ru.stazaev.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String secret_key;

    @Column
    @JsonProperty("BTC_wallet")
    private double BTC_wallet;

    @Column
    @JsonProperty("TON_wallet")
    private double TON_wallet;

    @Column
    @JsonProperty("RUB_wallet")
    private double RUB_wallet;

}
