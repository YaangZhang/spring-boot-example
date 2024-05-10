package com.chagee.domain.service.impl;

import com.chagee.domain.entity.Account;
import com.chagee.domain.exception.DailyLimitExceededException;
import com.chagee.domain.modal.ExchangeRate;
import com.chagee.domain.modal.Money;
import com.chagee.domain.service.AccountTransferService;
import org.springframework.stereotype.Service;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) throws Exception, DailyLimitExceededException {
        Money sourceMoney =  exchangeRate.exchageTo(targetMoney);
        sourceAccount.withdraw(sourceMoney);
        targetAccount.deposit(targetMoney);
    }
}
