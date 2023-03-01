package ru.stazaev.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.stazaev.entity.DTO.ExchangeRequestDTO;
import ru.stazaev.entity.DTO.ExchangeResponseDTO;
import ru.stazaev.entity.DTO.WalletWithdrawal;
import ru.stazaev.entity.DTO.walletDto.WalletAbstractClass;
import ru.stazaev.entity.Wallet;
import ru.stazaev.service.OperationService;
import ru.stazaev.service.WalletService;


@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @RequestMapping(value = "/replenish", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(
            description = "Пополнение баланса"
    )
    public WalletAbstractClass replenish(@RequestBody Wallet wallet) {
        return operationService.replenishBalance(wallet);
    }

    @Operation(
            description = "Вывод баланса"
    )
    @RequestMapping(value = "/withdrawal", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public WalletAbstractClass replenish(@RequestBody WalletWithdrawal wallet) {
        return operationService.withdrawalBalance(wallet);
    }

    @Operation(
            description = "Просмотр курса"
    )
    @RequestMapping(value = "/exchangeRate", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public WalletAbstractClass getExchangeRate(@RequestBody WalletWithdrawal walletWithdrawal) {
        return operationService.ExchangeRate(walletWithdrawal);
    }

    @Operation(
            description = "Обмен валют"
    )
    @RequestMapping(value = "/exchange", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ExchangeResponseDTO exchange(@RequestBody ExchangeRequestDTO requestDTO) {
        return operationService.exchange(requestDTO);
    }
}
