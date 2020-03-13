package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.entities.Hero;
import com.mkolongo.heros.domain.models.binding.HeroCreateModel;
import com.mkolongo.heros.domain.models.view.HeroDetailsModel;
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
    public HeroDetailsModel getHeroByUsername(String username) {
        return heroRepository.findByUser_Username(username)
                .map(hero -> {
                    HeroDetailsModel detailsModel = mapper.map(hero, HeroDetailsModel.class);
                    if ("MALE".equals(hero.getGender().name())) {
                        detailsModel.setGender("/img/male.jpg");
                    } else {
                        detailsModel.setGender("/img/female.jpg");
                    }
                    return detailsModel;
                }).get();
    }

    @Override
    public String fight(String username, String opponentName) {
        Hero hero = heroRepository.findByUser_Username(username).orElseThrow();
        Hero opponentHero = heroRepository.findByName(opponentName).orElseThrow();

        int heroDamage = hero.getAttack() + (hero.getStrength() * 4) -
                          opponentHero.getDefence() + (opponentHero.getStamina() * 2);

        int opponentDamage = opponentHero.getAttack() + (opponentHero.getStrength() * 4) -
                             hero.getDefence() + (hero.getStamina() * 2);

        if (heroDamage > opponentDamage) {
            hero.setLevel(hero.getLevel() + 1);
            hero.setStrength(hero.getStrength() + 5);
            hero.setStamina(hero.getStamina() + 5);
            heroRepository.save(hero);
            return hero.getName();
        } else {
            opponentHero.setLevel(opponentHero.getLevel() + 1);
            opponentHero.setStamina(opponentHero.getStamina() + 5);
            opponentHero.setStamina(opponentHero.getStrength() + 5);
            heroRepository.save(opponentHero);
            return opponentHero.getName();
        }
    }

    @Override
    public HeroViewModel getHeroByName(String heroName) {
        return heroRepository.findByName(heroName)
                .map(hero -> {
                    HeroViewModel viewModel = mapper.map(hero, HeroViewModel.class);
                    if ("MALE".equals(hero.getGender().name())) {
                        viewModel.setGender("/img/male.jpg");
                    } else {
                        viewModel.setGender("/img/female.jpg");
                    }
                    return viewModel;
                }).get();

    }
}
