package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.models.service.UserServiceModel;
import com.mkolongo.heros.domain.models.view.UserProfileModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(UserServiceModel serviceModel);

    UserProfileModel getProfile(String username);

//    List<UserProfileModel> getAllUsers();

    boolean hasHero(String username);
}
