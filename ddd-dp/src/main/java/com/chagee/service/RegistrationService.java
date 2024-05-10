package com.chagee.service;

import com.chagee.domain.PhoneNumber;
import com.chagee.domain.User;

public interface RegistrationService {
    public User register(String name, String phone, String address);
    public User register(String name, PhoneNumber phone, String address);

}
