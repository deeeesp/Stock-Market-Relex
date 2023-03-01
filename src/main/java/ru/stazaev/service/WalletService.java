package ru.stazaev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stazaev.entity.DTO.WalletWithdrawal;
import ru.stazaev.entity.DTO.WalletWithoutKey;
import ru.stazaev.entity.DTO.walletDto.BtcWalletDTO;
import ru.stazaev.entity.DTO.walletDto.RubWalletDTO;
import ru.stazaev.entity.DTO.walletDto.TonWalletDTO;
import ru.stazaev.entity.DTO.walletDto.WalletAbstractClass;
import ru.stazaev.entity.Wallet;
import ru.stazaev.repository.WalletRepository;
import ru.stazaev.service.mapper.WalletMapper;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    @Autowired
    private WalletMapper walletMapper;

    public Wallet findBySecretKey(String key) {
        return walletRepository.findBySecretKey(key);
    }

    public Wallet findBySecretKey(Wallet wallet) {
        return walletRepository.findBySecretKey(wallet.getSecret_key());
    }

    public WalletWithoutKey getBalanceByCurrency(Wallet wallet) {
        var walletFromDB = findBySecretKey(wallet);
        return walletMapper.walletToDto(walletFromDB);
    }

    public double getBalanceByCurrency(String key, String currency) {
        switch (currency) {
            case "RUB" -> {
                return walletRepository.findBySecretKey(key).getRUB_wallet();
            }
            case "BTC" -> {
                return walletRepository.findBySecretKey(key).getBTC_wallet();
            }
            case "TON" -> {
                return walletRepository.findBySecretKey(key).getTON_wallet();
            }
        }
        return 0;
    }

    public WalletAbstractClass replenishBalance(String key, String currency, double value) {
        switch (currency) {
            case "RUB" -> {
                if ((findBySecretKey(key).getRUB_wallet() + value) > 0) {
                    walletRepository.updateRubBalance(key, value);
                    var balance = findBySecretKey(key).getRUB_wallet();
                    return RubWalletDTO.builder()
                            .RUB_wallet(balance)
                            .build();
                }
            }
            case "BTC" -> {
                if ((findBySecretKey(key).getBTC_wallet() + value) > 0) {
                    walletRepository.updateBtcBalance(key, value);
                    var balance = findBySecretKey(key).getBTC_wallet();
                    return BtcWalletDTO.builder()
                            .BTC_wallet(balance)
                            .build();
                }
            }
            case "TON" -> {
                if ((findBySecretKey(key).getTON_wallet() + value) > 0) {
                    walletRepository.updateTonBalance(key, value);
                    var balance = findBySecretKey(key).getTON_wallet();
                    return TonWalletDTO.builder()
                            .TON_wallet(balance)
                            .build();
                }
            }
        }
        return null;
    }

    public WalletAbstractClass replenishBalance(Wallet wallet) {
        if (wallet.getRUB_wallet() != 0) {
            var value = wallet.getRUB_wallet();
            walletRepository.updateRubBalance(wallet.getSecret_key(), value);
            var balance = getBalanceByCurrency(wallet).getRUB_wallet();
            return RubWalletDTO.builder()
                    .RUB_wallet(balance)
                    .build();

        } else if (wallet.getBTC_wallet() != 0) {
            var value = wallet.getBTC_wallet();
            walletRepository.updateBtcBalance(wallet.getSecret_key(), value);
            var balance = getBalanceByCurrency(wallet).getBTC_wallet();
            return BtcWalletDTO.builder()
                    .BTC_wallet(balance)
                    .build();
        } else if (wallet.getTON_wallet() != 0) {
            var value = wallet.getTON_wallet();
            walletRepository.updateTonBalance(wallet.getSecret_key(), value);
            var balance = getBalanceByCurrency(wallet).getTON_wallet();
            return TonWalletDTO.builder()
                    .TON_wallet(balance)
                    .build();
        }
        return null;
    }

    public double getValueByField(Wallet wallet) {
        if (wallet.getRUB_wallet() != 0) {
            return wallet.getRUB_wallet();
        } else if (wallet.getBTC_wallet() != 0) {
            return wallet.getBTC_wallet();
        } else if (wallet.getTON_wallet() != 0) {
            return wallet.getTON_wallet();
        }
        return 0;
    }

    public String getNameByField(Wallet wallet) {
        if (wallet.getRUB_wallet() != 0) {
            return "RUB";
        } else if (wallet.getBTC_wallet() != 0) {
            return "BTC";
        } else if (wallet.getTON_wallet() != 0) {
            return "TON";
        }
        return "";
    }

    public WalletAbstractClass countAllCurrency(WalletWithdrawal walletWithdrawal){
        var currency = walletWithdrawal.getCurrency();
        switch (currency) {
            case "RUB" -> {
                return new RubWalletDTO(walletRepository.countAllByRub());
            }
            case "BTC" -> {
                return new BtcWalletDTO(walletRepository.countAllByBtc());
            }
            case "TON" -> {
                return new TonWalletDTO(walletRepository.countAllByTon());
            }
        }
        return null;
    }

}
