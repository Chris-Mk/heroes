package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.models.binding.HeroCreateModel;
import com.mkolongo.heros.domain.models.view.HeroDetailsModel;
import com.mkolongo.heros.domain.models.view.HeroViewModel;

import java.util.List;

public interface HeroService {

    void create(HeroCreateModel heroCreateModel, String username);

    List<HeroViewModel> getAllHeroes(String heroName);

    HeroDetailsModel getHeroByUsername(String username);

    String fight(String username, String opponentName);

    HeroViewModel getHeroByName(String heroName);
}
