package com.chagee.service.impl;

import com.chagee.domain.ExchangeRate;
import com.chagee.domain.Money;
import com.chagee.service.BankService;
import com.chagee.service.ExchangeService;
import com.chagee.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    public BankService bankService;
    @Autowired
    public ExchangeService exchangeService;

    @Override
    public void pay(BigDecimal money, Long recipientId) {

        bankService.transfer(money, "CNY", recipientId);
    }

    @Override
    public void pay(Money money, Long recipientId) {
        bankService.transfer(money, recipientId);
    }

    @Override
    public void pay(Money money, Currency targetCurrency, Long recipientId) {
//        BigDecimal rate = exchangeService.getRate(money.getCurrency(), targetCurrency);
//        BigDecimal targetAmount = money.getAmount().multiply(rate);
//        Money targetMoney = new Money(targetAmount, targetCurrency);

        ExchangeRate rate = exchangeService.getRateNew(money.getCurrency(), targetCurrency);
        Money targetMoney = rate.exchange(money);

        bankService.transfer(targetMoney, recipientId);
    }
}
