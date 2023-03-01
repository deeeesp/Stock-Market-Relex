package ru.stazaev.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.stazaev.entity.DTO.CountOperationsRequest;
import ru.stazaev.entity.DTO.CountOperationsResponse;
import ru.stazaev.entity.DTO.SetExchangeRateDTO;
import ru.stazaev.entity.DTO.WalletWithdrawal;
import ru.stazaev.entity.DTO.walletDto.WalletAbstractClass;
import ru.stazaev.service.ExchangeRateService;
import ru.stazaev.service.OperationService;
import ru.stazaev.service.WalletService;

import java.text.ParseException;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private OperationService operationService;

    @Operation(
            description = "Изменить курс валют"
    )
    @RequestMapping(value = "/setrate", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public WalletAbstractClass setExchangeRate(@RequestBody SetExchangeRateDTO exchangeRateDTO) {
        System.out.println(exchangeRateDTO);
        return exchangeRateService.setExchangeRate(exchangeRateDTO);
    }

    @Operation(
            description = "Посмотреть общую сумму"
    )
    @RequestMapping(value = "/currency", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public WalletAbstractClass amountCurrency(@RequestBody WalletWithdrawal walletWithdrawal) {
        return walletService.countAllCurrency(walletWithdrawal);
    }

    @Operation(
            description = "Посмотреть количество операций"
    )
    @RequestMapping(value = "/count", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CountOperationsResponse countOperations(@RequestBody CountOperationsRequest operationsRequest) throws ParseException {
        System.out.println(operationsRequest.getDate_to());
        System.out.println(operationsRequest.getDate_from());
        return operationService.countOperations(operationsRequest);
    }

}
