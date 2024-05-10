package com.chagee.dao;


import com.chagee.domain.entity.Account;

public interface AccountBuilder {
    Account toAccount(AccountDO accountDO) throws Exception;

    AccountDO fromAccount(Account account);
}
