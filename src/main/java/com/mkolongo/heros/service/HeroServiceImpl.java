package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.entities.Hero;
import com.mkolongo.heros.domain.models.binding.HeroCreateModel;
import com.mkolongo.heros.repository.HeroRepository;
import com.mkolongo.heros.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public void create(HeroCreateModel heroCreateModel, String username) {
        Hero hero = mapper.map(heroCreateModel, Hero.class);
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    user.setHero(hero);
                    hero.setUser(user);
                });

        heroRepository.save(hero);
    }
}
