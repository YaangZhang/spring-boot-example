package com.chagee.domain.modal;


import com.chagee.domain.entity.Account;

import java.util.Date;

public class AuditMessage {

    private UserId userId;
    private AccountNumber source;
    private AccountNumber target;
    private Money money;
    private Date date;

    public String serialize() {
        return userId + "," + source + "," + target + "," + money + "," + date;
    }

    public static AuditMessage deserialize(String value) {
        // todo
        return null;
    }

    public AuditMessage(Account sourceAccount, Account targetAccount, Money targetMoney) {

    }
}
