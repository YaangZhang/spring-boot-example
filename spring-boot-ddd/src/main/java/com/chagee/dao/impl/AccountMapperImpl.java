package com.chagee.dao.impl;

import com.chagee.dao.AccountDO;
import com.chagee.dao.AccountMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class AccountMapperImpl implements AccountMapper {
    public int insert(AccountDO accountDO) {
        System.err.println("------------------插入:"+ accountDO);
        return 0;
    }

    public int update(AccountDO accountDO) {
        System.err.println("------------------更新:"+ accountDO);
        return 0;
    }

    public AccountDO selectByUserId(Long id) {
        AccountDO accountDO = new AccountDO();
        accountDO.setCurrency("CNY");
        accountDO.setId(id);
        accountDO.setAccountNumber("1234");
        accountDO.setUserId(100L);
        accountDO.setAvailableAmout(new BigDecimal(10000));
        accountDO.setDailyLimitAmout(new BigDecimal(5000));

        return accountDO;
    }

    public AccountDO selectByAccountNumber(String accountNumber) {
        AccountDO accountDO = new AccountDO();
        accountDO.setCurrency("US");
        accountDO.setId(101L);
        accountDO.setAccountNumber(accountNumber);
        accountDO.setUserId(101L);
        accountDO.setAvailableAmout(new BigDecimal(10000));
        accountDO.setDailyLimitAmout(new BigDecimal(5000));

        return accountDO;
    }

    public AccountDO selectById(Long id) {
        return null;
    }
}
