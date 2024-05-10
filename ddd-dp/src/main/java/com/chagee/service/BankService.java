package com.chagee.service;

import com.chagee.domain.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankService {
    public void transfer(BigDecimal money,String currency, Long recipientId){

    }

    public void transfer(Money money, Long recipientId) {
        
    }
}
