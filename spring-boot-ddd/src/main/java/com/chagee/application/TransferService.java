package com.chagee.application;


import com.chagee.domain.exception.DailyLimitExceededException;
import com.chagee.domain.modal.Result;

import java.math.BigDecimal;

public interface TransferService {

    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) throws Exception, DailyLimitExceededException;

}