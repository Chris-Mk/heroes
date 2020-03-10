package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.entities.Hero;
import com.mkolongo.heros.domain.models.binding.HeroCreateModel;
import com.mkolongo.heros.domain.models.view.HeroViewModel;

import java.util.List;

public interface HeroService {

    void create(HeroCreateModel heroCreateModel, String username);

    List<HeroViewModel> getAllHeroes(String heroName);

    Hero getHeroByUsername(String username);
}
