package com.mkolongo.heros.domain.models.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroDetailsModel {

    private String name;
    private String gender;
    private int level;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;

}
