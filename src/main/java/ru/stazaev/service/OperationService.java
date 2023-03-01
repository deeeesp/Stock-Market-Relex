package ru.stazaev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stazaev.entity.DTO.*;
import ru.stazaev.entity.DTO.walletDto.WalletAbstractClass;
import ru.stazaev.entity.Operation;
import ru.stazaev.entity.Wallet;
import ru.stazaev.entity.enums.OperationType;
import ru.stazaev.repository.OperationRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;
    @Autowired
    private WalletService walletService;
    @Autowired
    private ExchangeRateService exchangeRateService;

    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    public WalletAbstractClass replenishBalance(Wallet wallet) {
        Operation operation = new Operation();
        operation.setSecret_key(wallet.getSecret_key());
        operation.setAmount_from(walletService.getValueByField(wallet));
        operation.setCurrency_from(walletService.getNameByField(wallet));
        operation.setDate(new java.sql.Date(new Date().getTime()));
        operation.setOperationType(OperationType.REPLENISH);
        save(operation);
        return walletService.replenishBalance(wallet);
    }

    public WalletAbstractClass withdrawalBalance(WalletWithdrawal walletWithdrawal) {
        Operation operation = new Operation();
        operation.setSecret_key(walletWithdrawal.getSecret_key());
        operation.setAmount_from(walletWithdrawal.getCount());
        operation.setCurrency_from(walletWithdrawal.getCurrency());
        operation.setDate(new java.sql.Date(new Date().getTime()));
        operation.setOperationType(OperationType.WITHDRAW);
        save(operation);
        return walletService.replenishBalance(walletWithdrawal.getSecret_key(), walletWithdrawal.getCurrency(), -walletWithdrawal.getCount());
    }

    public WalletAbstractClass ExchangeRate(WalletWithdrawal walletWithdrawal) {
        return exchangeRateService.getExchangeRate(walletWithdrawal.getCurrency());
    }

    public ExchangeResponseDTO exchange(ExchangeRequestDTO requestDTO) {
        Operation operation = new Operation();
        operation.setSecret_key(requestDTO.getSecret_key());
        operation.setAmount_from(requestDTO.getAmount());
        operation.setCurrency_from(requestDTO.getCurrency_from());
        operation.setDate(new java.sql.Date(new Date().getTime()));
        operation.setOperationType(OperationType.REPLENISH);
        var exchange = exchangeRateService.exchange(requestDTO);
        operation.setAmount_to(exchange.getAmount_to());
        operation.setCurrency_to(exchange.getCurrency_to());
        operation.setOperationType(OperationType.CHANGE);
        save(operation);
        return exchange;
    }

    public CountOperationsResponse countOperations(CountOperationsRequest operationsRequest) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        var fromFormat = dateFormat.parse(operationsRequest.getDate_from());
        var toFormat = dateFormat.parse(operationsRequest.getDate_to());
        var from = new java.sql.Date(fromFormat.getTime());
        var to = new java.sql.Date(toFormat.getTime());
        return CountOperationsResponse.builder()
                .transaction_count(operationRepository.countOperations(from, to).size())
                .build();
    }
}
