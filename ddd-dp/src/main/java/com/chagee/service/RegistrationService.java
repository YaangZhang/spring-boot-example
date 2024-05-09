package com.chagee.service;

import com.chagee.domain.User;

public interface RegistrationService {
    public User register(String name, String phone, String address);

}
