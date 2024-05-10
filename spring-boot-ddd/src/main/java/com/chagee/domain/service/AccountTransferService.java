package com.chagee.domain.service;


import com.chagee.domain.entity.Account;
import com.chagee.domain.exception.DailyLimitExceededException;
import com.chagee.domain.modal.ExchangeRate;
import com.chagee.domain.modal.Money;

public interface AccountTransferService {

    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) throws Exception, DailyLimitExceededException;
}
