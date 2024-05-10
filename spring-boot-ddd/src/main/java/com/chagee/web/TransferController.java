package com.chagee.web;

import com.chagee.application.TransferService;
import com.chagee.domain.exception.DailyLimitExceededException;
import com.chagee.domain.modal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    Result<Boolean> transfer(@RequestParam Long sourceUserId,
                    @RequestParam String targetAccountNumber,
                    @RequestParam BigDecimal targetAmount,
                    @RequestParam String targetCurrency) {
        Result<Boolean> transfer ;
        try {
            transfer = transferService.transfer(sourceUserId, targetAccountNumber, targetAmount, targetCurrency);
        } catch (Exception e) {
            transfer = Result.fail(false);
            throw new RuntimeException(e);
        } catch (DailyLimitExceededException e) {
            transfer = Result.fail(false);
            throw new RuntimeException(e);
        }
        return transfer;
    }
}
