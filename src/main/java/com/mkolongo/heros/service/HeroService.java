package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.models.binding.HeroCreateModel;

public interface HeroService {

    void create(HeroCreateModel heroCreateModel, String username);
}
