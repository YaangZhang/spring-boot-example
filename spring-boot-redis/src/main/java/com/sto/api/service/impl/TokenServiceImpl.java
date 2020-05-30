package com.sto.api.service.impl;

import com.sto.api.service.TokenService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author zhy
 * @create 2020-04-10-12:01
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        return false;
    }
}
