package com.chagee.dao.impl;

import com.chagee.dao.AccountBuilder;
import com.chagee.dao.AccountDO;
import com.chagee.domain.entity.Account;
import com.chagee.domain.modal.AccountId;
import com.chagee.domain.modal.AccountNumber;
import com.chagee.domain.modal.Currency;
import com.chagee.domain.modal.Money;
import com.chagee.domain.modal.UserId;
import org.springframework.stereotype.Component;

@Component
public class AccountBuilderImpl implements AccountBuilder {
    public Account toAccount(AccountDO accountDO) throws Exception {
        Account account = new Account();
        account.setId(new AccountId(accountDO.getId()));
        account.setAccountNumber(new AccountNumber(accountDO.getAccountNumber()));
        account.setUserId(new UserId(accountDO.getUserId()));
        Currency currency = new Currency(accountDO.getCurrency());
        account.setAvailable(new Money(accountDO.getAvailableAmout(), currency));
        account.setDailyLimit(new Money(accountDO.getDailyLimitAmout(), currency));
        account.setCurrency(currency);
        return account;
    }

    public AccountDO fromAccount(Account account) {
        AccountDO accountDO = new AccountDO();
        if (account.getId() != null){
            accountDO.setId(account.getId().getValue());
        }
        accountDO.setUserId(account.getUserId().getId());
        accountDO.setAccountNumber(account.getAccountNumber().getValue());
        accountDO.setAvailableAmout(account.getAvailable().getAmout());
        accountDO.setDailyLimitAmout(account.getDailyLimit().getAmout());
        accountDO.setCurrency(account.getCurrency().getValue());
        return accountDO;
    }
}
