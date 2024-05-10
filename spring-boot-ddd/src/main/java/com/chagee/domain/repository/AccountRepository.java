package com.chagee.domain.repository;

import com.chagee.domain.entity.Account;
import com.chagee.domain.modal.AccountId;
import com.chagee.domain.modal.AccountNumber;
import com.chagee.domain.modal.UserId;

public interface AccountRepository {
    Account find(AccountId id) throws Exception;
    Account find(AccountNumber accountNumber) throws Exception;
    Account find(UserId userId) throws Exception;
    Account save(Account account) throws Exception;
}