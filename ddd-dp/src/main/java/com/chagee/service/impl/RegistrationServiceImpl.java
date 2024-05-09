package com.chagee.service.impl;

import com.chagee.domain.SalesRep;
import com.chagee.domain.User;
import com.chagee.repository.SalesRepRepository;
import com.chagee.repository.UserRepository;
import com.chagee.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.util.Arrays;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private SalesRepRepository salesRepRepo;
    @Autowired
    private UserRepository userRepo;

    @Override
    public User register(String name, String phone, String address) {
        if (StringUtils.isEmpty(name)) {
            throw new ValidationException("name");
        }if (StringUtils.isEmpty(phone) || !isValidPhoneNumber(phone)) {
            throw new ValidationException("phone");
        }

        // 取电话号里的区号，然后通过区号找到区域内的SalesRep
        String areaCode = getCode(phone);
        SalesRep rep = salesRepRepo.findRep(areaCode);

        // 最后创建用户，落盘，然后返回
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);
        if (rep != null) {
            user.setRepId(rep.getRepId());
        }

        return userRepo.save(user);
    }

    private String getCode(String phone){
        for (int i = 0; i < phone.length(); i++) {
            String prefix = phone.substring(0, i);
            if (isAreaCode(prefix)) {
                return prefix;
            }
        }
        return null;
    }
    private static boolean isAreaCode(String prefix) {
        String[] areas = new String[]{"0571", "021"};
        return Arrays.asList(areas).contains(prefix);
    }

    private boolean isValidPhoneNumber(String phone) {
        String pattern = "^0[1-9]{2,3}-?\\d{8}$";
        return phone.matches(pattern);
    }
}
