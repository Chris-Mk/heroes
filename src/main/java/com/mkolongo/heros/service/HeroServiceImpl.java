package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.entities.Gender;
import com.mkolongo.heros.domain.entities.Hero;
import com.mkolongo.heros.domain.models.binding.HeroCreateModel;
import com.mkolongo.heros.domain.models.view.HeroViewModel;
import com.mkolongo.heros.repository.HeroRepository;
import com.mkolongo.heros.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<HeroViewModel> getAllHeroes(String username) {
        return heroRepository.findAll()
                .stream()
                .filter(hero -> !hero.getUser().getUsername().equals(username))
                .map(hero -> {
                    HeroViewModel viewModel = mapper.map(hero, HeroViewModel.class);
                    if ("MALE".equals(hero.getGender().name())) {
                        viewModel.setGender("/img/male.jpg");
                    } else {
                        viewModel.setGender("/img/female.jpg");
                    }
                    return viewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Hero getHeroByUsername(String username) {
        return heroRepository.findByUser_Username(username).orElseThrow();
    }
}
