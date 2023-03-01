package ru.stazaev.service.mapper;

import org.springframework.stereotype.Service;
import ru.stazaev.entity.DTO.WalletWithoutKey;
import ru.stazaev.entity.Wallet;

@Service
public class WalletMapper {

    public WalletWithoutKey walletToDto(Wallet wallet){
        var rub = wallet.getRUB_wallet();
        var btc = wallet.getBTC_wallet();
        var ton = wallet.getTON_wallet();
        System.out.println(wallet);
//        WalletWithoutKey walletWithoutKey = WalletWithoutKey.builder()
//                .RUB_wallet(rub)
//                .BTC_wallet(btc)
//                .TON_wallet(ton)
//                .build();
        WalletWithoutKey walletWithoutKey = new WalletWithoutKey();
        walletWithoutKey.setBTC_wallet(btc);
        walletWithoutKey.setRUB_wallet(rub);
        walletWithoutKey.setTON_wallet(ton);
        System.out.println(walletWithoutKey);
        return  walletWithoutKey;
    }
}
