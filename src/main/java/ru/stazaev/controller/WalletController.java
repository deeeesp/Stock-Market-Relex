package ru.stazaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.stazaev.entity.DTO.WalletWithoutKey;
import ru.stazaev.entity.Wallet;
import ru.stazaev.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/balance", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public WalletWithoutKey getBalance(@RequestBody Wallet wallet){
        return walletService.getBalanceByCurrency(wallet);
    }
}
