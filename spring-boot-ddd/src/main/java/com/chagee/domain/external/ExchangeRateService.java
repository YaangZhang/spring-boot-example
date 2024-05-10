package com.chagee.domain.external;


import com.chagee.domain.modal.Currency;
import com.chagee.domain.modal.ExchangeRate;

public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);

}

