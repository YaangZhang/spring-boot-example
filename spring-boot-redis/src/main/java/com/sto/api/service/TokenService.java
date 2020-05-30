package com.sto.api.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhy
 * @create 2020-04-10-11:58
 */
public interface TokenService {

    /**
     * 创建token
     * @return
     */
    public String createToken();

    /**
     * 校验token
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request);
}
