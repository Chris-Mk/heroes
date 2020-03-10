package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.models.service.UserServiceModel;
import com.mkolongo.heros.domain.models.view.UserProfileModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(UserServiceModel serviceModel);

    UserProfileModel getProfile(String username);
}
