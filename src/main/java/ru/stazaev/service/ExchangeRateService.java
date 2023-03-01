package ru.stazaev.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stazaev.entity.DTO.ExchangeRequestDTO;
import ru.stazaev.entity.DTO.ExchangeResponseDTO;
import ru.stazaev.entity.DTO.SetExchangeRateDTO;
import ru.stazaev.entity.DTO.walletDto.BtcAndRubWalletDTO;
import ru.stazaev.entity.DTO.walletDto.BtcAndTonWalletDTO;
import ru.stazaev.entity.DTO.walletDto.TonAndRubWalletDTO;
import ru.stazaev.entity.DTO.walletDto.WalletAbstractClass;
import ru.stazaev.entity.ExchangeRate;
import ru.stazaev.repository.ExchangeRateRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private WalletService walletService;

    public void deleteAll(){
        exchangeRateRepository.deleteAll();
    }

    public void save(ExchangeRate exchangeRate){
        exchangeRateRepository.save(exchangeRate);
    }

    public List<ExchangeRate> findAll() {
        return exchangeRateRepository.findAll();
    }

    public WalletAbstractClass getExchangeRate(String currency) {
        var exchangeRate = findAll().get(0);
        var BTCVal = exchangeRate.getBTC();
        var TONVal = exchangeRate.getTON();
        var RUBVal = exchangeRate.getRUB();
        switch (currency) {
            case "RUB" -> {
                var BTC = BTCVal / RUBVal;
                var TON = TONVal / RUBVal;
                return BtcAndTonWalletDTO.builder()
                        .BTC_wallet(BTC)
                        .TON_wallet(TON)
                        .build();
            }
            case "BTC" -> {
                var RUB = RUBVal / BTCVal;
                var TON = TONVal / BTCVal;
                return TonAndRubWalletDTO.builder()
                        .RUB_wallet(RUB)
                        .TON_wallet(TON)
                        .build();
            }
            case "TON" -> {
                var RUB = RUBVal / TONVal;
                var BTC = BTCVal / TONVal;
                return BtcAndRubWalletDTO.builder()
                        .BTC_wallet(BTC)
                        .RUB_wallet(RUB)
                        .build();
            }
        }
        return null;
    }

    public ExchangeResponseDTO exchange(ExchangeRequestDTO requestDTO) {
        double amount = requestDTO.getAmount();
        var from = requestDTO.getCurrency_from();
        var to = requestDTO.getCurrency_to();
        var key = requestDTO.getSecret_key();
        if (amount < walletService.getBalanceByCurrency(key, from)) {
            walletService.replenishBalance(key, from, -amount);
            double amountTo = calculateExchange(from, to, amount);
            walletService.replenishBalance(key, to, amountTo);
            return new ExchangeResponseDTO(from, to, amount, amountTo);
        }
        return new ExchangeResponseDTO(from, to, 0, 0);
    }

    public double calculateExchange(String from, String to, double amount) {
        double fromAmount = getValByCurrency(from);
        double toAmount = getValByCurrency(to);
        return toAmount * amount / fromAmount;
    }

    public double getValByCurrency(String currency) {
        ExchangeRate exchangeRate = findAll().get(0);
        switch (currency) {
            case "RUB" -> {
                return exchangeRate.getRUB();
            }
            case "BTC" -> {
                return exchangeRate.getBTC();
            }
            case "TON" -> {
                return exchangeRate.getTON();
            }
        }
        return 0;
    }

    public WalletAbstractClass setExchangeRate(SetExchangeRateDTO exchangeRateDTO){
        System.out.println(exchangeRateDTO);
        var currency = exchangeRateDTO.getBase_currency();
        switch (exchangeRateDTO.getBase_currency()) {
            case "RUB":
                deleteAll();
                save(new ExchangeRate(1, exchangeRateDTO.getTON(), exchangeRateDTO.getBTC()));
                return getExchangeRate(currency);
            case "BTC":
                deleteAll();
                save(new ExchangeRate(exchangeRateDTO.getRUB(), exchangeRateDTO.getTON(), 1));
                return getExchangeRate(currency);
            case "TON":
                deleteAll();
                save(new ExchangeRate(exchangeRateDTO.getRUB(), 1, exchangeRateDTO.getBTC()));
                return getExchangeRate(currency);
        }
        return null;
    }
}
