package com.chagee.domain.modal;


import org.springframework.util.StringUtils;

public class AccountNumber {

    private String value;

    public AccountNumber(String value) {
        if (StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("账号不能为空");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
