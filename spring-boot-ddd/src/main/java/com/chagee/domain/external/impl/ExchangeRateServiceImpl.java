package com.chagee.domain.external.impl;

import com.chagee.domain.external.ExchangeRateService;
import com.chagee.domain.modal.Currency;
import com.chagee.domain.modal.ExchangeRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    public ExchangeRate getExchangeRate(Currency source, Currency target) {
        if (source.equals(target)) {
        return new ExchangeRate(BigDecimal.ONE, source, target);
        }
        BigDecimal forex = new BigDecimal("2.00");
        return new ExchangeRate(forex, source, target);
    }
}
