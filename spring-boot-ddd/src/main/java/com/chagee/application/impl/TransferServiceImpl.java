package com.chagee.application.impl;

import com.chagee.application.TransferService;
import com.chagee.domain.entity.Account;
import com.chagee.domain.exception.DailyLimitExceededException;
import com.chagee.domain.external.ExchangeRateService;
import com.chagee.domain.messaging.AuditMessageProducer;
import com.chagee.domain.modal.AccountNumber;
import com.chagee.domain.modal.AuditMessage;
import com.chagee.domain.modal.Currency;
import com.chagee.domain.modal.ExchangeRate;
import com.chagee.domain.modal.Money;
import com.chagee.domain.modal.Result;
import com.chagee.domain.modal.UserId;
import com.chagee.domain.repository.AccountRepository;
import com.chagee.domain.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;

//    private AuditMessageProducer auditMessageProducer;
    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private AccountTransferService accountTransferService;

//    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) throws Exception, DailyLimitExceededException {
        Money targetMoney = new Money(targetAmount, new Currency(targetCurrency));

        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));

        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));

        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        accountRepository.save(sourceAccount);

        accountRepository.save(targetAccount);

        // 发送审计消息
        AuditMessage message = new AuditMessage(sourceAccount, targetAccount, targetMoney);
//        auditMessageProducer.send(message);

        return Result.success(true);
    }
}
