package com.chagee.service;

import com.chagee.domain.Money;

import java.math.BigDecimal;
import java.util.Currency;

public interface TransferService {

    public void pay(BigDecimal money, Long recipientId);

    public void pay(Money money, Long recipientId);

    public void pay(Money money, Currency targetCurrency, Long recipientId);
}
