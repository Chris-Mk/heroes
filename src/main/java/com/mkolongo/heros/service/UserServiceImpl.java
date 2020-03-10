package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.entities.Gender;
import com.mkolongo.heros.domain.entities.User;
import com.mkolongo.heros.domain.entities.UserPrincipal;
import com.mkolongo.heros.domain.models.service.UserServiceModel;
import com.mkolongo.heros.domain.models.view.UserProfileModel;
import com.mkolongo.heros.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("Not found!"));
    }

    @Override
    public void register(UserServiceModel serviceModel) {
        String password = passwordEncoder.encode(serviceModel.getPassword());
        User user = mapper.map(serviceModel, User.class);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public UserProfileModel getProfile(String username) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    UserProfileModel profileModel = mapper.map(user, UserProfileModel.class);
                    if (Gender.MALE.name().equals(profileModel.getHeroGender())) {
                        profileModel.setHeroGender("/img/male.jpg");
                    } else {
                        profileModel.setHeroGender("/img/female/jpg");
                    }
                    return profileModel;
                })
                .orElseThrow(() -> new UsernameNotFoundException("Not found!"));
    }
}
