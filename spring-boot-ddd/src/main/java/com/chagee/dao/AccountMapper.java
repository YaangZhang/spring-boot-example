package com.chagee.dao;

public interface AccountMapper {
    int insert(AccountDO accountDO);

    int update(AccountDO accountDO);

    AccountDO selectByUserId(Long id);

    AccountDO selectByAccountNumber(String accountNumber);

    AccountDO selectById(Long id);
}
