package com.lin.community.service;

import com.lin.community.dto.enums.ActivationResultEnum;
import com.lin.community.dto.enums.RegisterResultEnum;
import com.lin.community.pojo.User;

public interface UserService {
    RegisterResultEnum register(User user);
    ActivationResultEnum activation(Integer id, String activationCode);
}
