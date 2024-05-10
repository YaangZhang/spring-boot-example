package com.chagee.service;

import com.chagee.domain.ExchangeRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
public class ExchangeService {

    public BigDecimal getRate(Currency currency, Currency targetCurrency){
        if (currency.equals(targetCurrency)) {
            return new BigDecimal("1.00");
        }
        // from third server
        return new BigDecimal("2.00");
    }

    public ExchangeRate getRateNew(Currency currency, Currency targetCurrency){
        BigDecimal rate = BigDecimal.ONE;
        if (!currency.equals(targetCurrency)) {
            rate = new BigDecimal("2.00");
        }
        // from third server
        return new ExchangeRate(rate, currency, targetCurrency);

    }


}
