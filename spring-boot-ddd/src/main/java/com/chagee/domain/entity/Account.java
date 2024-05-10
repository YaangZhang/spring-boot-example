package com.chagee.domain.entity;

import com.chagee.domain.exception.DailyLimitExceededException;
import com.chagee.domain.exception.InsufficientFundsException;
import com.chagee.domain.modal.AccountId;
import com.chagee.domain.modal.AccountNumber;
import com.chagee.domain.modal.Currency;
import com.chagee.domain.modal.InvalidCurrencyException;
import com.chagee.domain.modal.Money;
import com.chagee.domain.modal.MoneyAmoutNotNullException;
import com.chagee.domain.modal.UserId;

//@Data
public class Account {
    private AccountId id;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;

    private Currency currency;

    // 转出
    public void withdraw(Money money) throws Exception, DailyLimitExceededException {
        if (this.available.compareTo(money) < 0){
            throw new InsufficientFundsException();
        }

        if (this.dailyLimit.compareTo(money) < 0){
            throw new DailyLimitExceededException();
        }

        this.available = this.available.subtract(money);
    }

    // 转入
    public void deposit(Money money) throws InvalidCurrencyException, MoneyAmoutNotNullException {
        if (!this.getCurrency().getValue().equals(money.getCurrency().getValue())){
            throw new InvalidCurrencyException();
        }

        this.available = this.available.add(money);

    }

    public AccountId getId() {
        return id;
    }

    public void setId(AccountId id) {
        this.id = id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public Money getAvailable() {
        return available;
    }

    public void setAvailable(Money available) {
        this.available = available;
    }

    public Money getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Money dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}