package com.chagee.domain.modal;


public class Currency {

    private String value;

    public Currency(String value) {
        if (value == null || value.isEmpty()){
            throw new IllegalArgumentException("货币不能为空");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "value='" + value + '\'' +
                '}';
    }
}
